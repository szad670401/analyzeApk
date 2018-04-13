package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.c.a;
import com.facebook.common.internal.g;
import com.facebook.common.internal.h;
import com.facebook.common.internal.k;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.c;
import com.facebook.common.memory.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

public abstract class BasePool<V> implements e<V> {
    private final Class<?> TAG = getClass();
    private boolean mAllowNewBuckets;
    final SparseArray<Bucket<V>> mBuckets;
    @GuardedBy
    final Counter mFree;
    final Set<V> mInUseValues;
    final c mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    @GuardedBy
    final Counter mUsed;

    @NotThreadSafe
    static class Counter {
        private static final String TAG = "com.facebook.imagepipeline.memory.BasePool.Counter";
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void increment(int i) {
            this.mCount++;
            this.mNumBytes += i;
        }

        public void decrement(int i) {
            if (this.mNumBytes < i || this.mCount <= 0) {
                a.c(TAG, "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(i), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
                return;
            }
            this.mCount--;
            this.mNumBytes -= i;
        }

        public void reset() {
            this.mCount = 0;
            this.mNumBytes = 0;
        }
    }

    public static class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object obj) {
            super("Invalid size: " + obj.toString());
        }
    }

    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int i, int i2, int i3, int i4) {
            super("Pool hard cap violation? Hard cap = " + i + " Used size = " + i2 + " Free size = " + i3 + " Request size = " + i4);
        }
    }

    protected abstract V alloc(int i);

    protected abstract void free(V v);

    protected abstract int getBucketedSize(int i);

    protected abstract int getBucketedSizeForValue(V v);

    protected abstract int getSizeInBytes(int i);

    public BasePool(c cVar, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.mMemoryTrimmableRegistry = (c) g.a((Object) cVar);
        this.mPoolParams = (PoolParams) g.a((Object) poolParams);
        this.mPoolStatsTracker = (PoolStatsTracker) g.a((Object) poolStatsTracker);
        this.mBuckets = new SparseArray();
        initBuckets(new SparseIntArray(0));
        this.mInUseValues = h.a();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    protected void initialize() {
        this.mMemoryTrimmableRegistry.a(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    public V get(int i) {
        V v;
        ensurePoolSizeInvariant();
        int bucketedSize = getBucketedSize(i);
        synchronized (this) {
            Bucket bucket = getBucket(bucketedSize);
            if (bucket != null) {
                v = bucket.get();
                if (v != null) {
                    g.b(this.mInUseValues.add(v));
                    bucketedSize = getBucketedSizeForValue(v);
                    int sizeInBytes = getSizeInBytes(bucketedSize);
                    this.mUsed.increment(sizeInBytes);
                    this.mFree.decrement(sizeInBytes);
                    this.mPoolStatsTracker.onValueReuse(sizeInBytes);
                    logStats();
                    if (a.a(2)) {
                        a.a(this.TAG, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSize));
                    }
                }
            }
            int sizeInBytes2 = getSizeInBytes(bucketedSize);
            if (canAllocate(sizeInBytes2)) {
                this.mUsed.increment(sizeInBytes2);
                if (bucket != null) {
                    bucket.incrementInUseCount();
                }
                v = null;
                try {
                    v = alloc(bucketedSize);
                } catch (Throwable th) {
                    synchronized (this) {
                        this.mUsed.decrement(sizeInBytes2);
                        Bucket bucket2 = getBucket(bucketedSize);
                        if (bucket2 != null) {
                            bucket2.decrementInUseCount();
                        }
                        k.a(th);
                    }
                    return v;
                }
                synchronized (this) {
                    g.b(this.mInUseValues.add(v));
                    trimToSoftCap();
                    this.mPoolStatsTracker.onAlloc(sizeInBytes2);
                    logStats();
                    if (a.a(2)) {
                        a.a(this.TAG, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSize));
                    }
                }
            } else {
                throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes2);
            }
        }
        return v;
    }

    public void release(V v) {
        g.a((Object) v);
        int bucketedSizeForValue = getBucketedSizeForValue(v);
        int sizeInBytes = getSizeInBytes(bucketedSizeForValue);
        synchronized (this) {
            Bucket bucket = getBucket(bucketedSizeForValue);
            if (!this.mInUseValues.remove(v)) {
                a.c(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSizeForValue));
                free(v);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else if (bucket == null || bucket.isMaxLengthExceeded() || isMaxSizeSoftCapExceeded() || !isReusable(v)) {
                if (bucket != null) {
                    bucket.decrementInUseCount();
                }
                if (a.a(2)) {
                    a.a(this.TAG, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSizeForValue));
                }
                free(v);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else {
                bucket.release(v);
                this.mFree.increment(sizeInBytes);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueRelease(sizeInBytes);
                if (a.a(2)) {
                    a.a(this.TAG, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSizeForValue));
                }
            }
            logStats();
        }
    }

    public void trim(MemoryTrimType memoryTrimType) {
        trimToNothing();
    }

    protected void onParamsChanged() {
    }

    protected boolean isReusable(V v) {
        g.a((Object) v);
        return true;
    }

    private synchronized void ensurePoolSizeInvariant() {
        boolean z = !isMaxSizeSoftCapExceeded() || this.mFree.mNumBytes == 0;
        g.b(z);
    }

    private synchronized void initBuckets(SparseIntArray sparseIntArray) {
        synchronized (this) {
            g.a((Object) sparseIntArray);
            this.mBuckets.clear();
            SparseIntArray sparseIntArray2 = this.mPoolParams.bucketSizes;
            if (sparseIntArray2 != null) {
                for (int i = 0; i < sparseIntArray2.size(); i++) {
                    int keyAt = sparseIntArray2.keyAt(i);
                    this.mBuckets.put(keyAt, new Bucket(getSizeInBytes(keyAt), sparseIntArray2.valueAt(i), sparseIntArray.get(keyAt, 0)));
                }
                this.mAllowNewBuckets = false;
            } else {
                this.mAllowNewBuckets = true;
            }
        }
    }

    void trimToNothing() {
        int i = 0;
        List arrayList = new ArrayList(this.mBuckets.size());
        SparseIntArray sparseIntArray = new SparseIntArray();
        synchronized (this) {
            for (int i2 = 0; i2 < this.mBuckets.size(); i2++) {
                Bucket bucket = (Bucket) this.mBuckets.valueAt(i2);
                if (bucket.getFreeListSize() > 0) {
                    arrayList.add(bucket);
                }
                sparseIntArray.put(this.mBuckets.keyAt(i2), bucket.getInUseCount());
            }
            initBuckets(sparseIntArray);
            this.mFree.reset();
            logStats();
        }
        onParamsChanged();
        while (i < arrayList.size()) {
            bucket = (Bucket) arrayList.get(i);
            while (true) {
                Object pop = bucket.pop();
                if (pop == null) {
                    break;
                }
                free(pop);
            }
            i++;
        }
    }

    synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }

    synchronized void trimToSize(int i) {
        int min = Math.min((this.mUsed.mNumBytes + this.mFree.mNumBytes) - i, this.mFree.mNumBytes);
        if (min > 0) {
            if (a.a(2)) {
                a.a(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(i), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(min));
            }
            logStats();
            for (int i2 = 0; i2 < this.mBuckets.size() && r1 > 0; i2++) {
                Bucket bucket = (Bucket) this.mBuckets.valueAt(i2);
                while (min > 0) {
                    Object pop = bucket.pop();
                    if (pop == null) {
                        break;
                    }
                    free(pop);
                    min -= bucket.mItemSize;
                    this.mFree.decrement(bucket.mItemSize);
                }
            }
            logStats();
            if (a.a(2)) {
                a.a(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(i), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
            }
        }
    }

    synchronized Bucket<V> getBucket(int i) {
        Bucket<V> bucket;
        bucket = (Bucket) this.mBuckets.get(i);
        if (bucket == null && this.mAllowNewBuckets) {
            if (a.a(2)) {
                a.a(this.TAG, "creating new bucket %s", Integer.valueOf(i));
            }
            bucket = newBucket(i);
            this.mBuckets.put(i, bucket);
        }
        return bucket;
    }

    Bucket<V> newBucket(int i) {
        return new Bucket(getSizeInBytes(i), Integer.MAX_VALUE, 0);
    }

    synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean z;
        z = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (z) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return z;
    }

    synchronized boolean canAllocate(int i) {
        boolean z = false;
        synchronized (this) {
            int i2 = this.mPoolParams.maxSizeHardCap;
            if (i > i2 - this.mUsed.mNumBytes) {
                this.mPoolStatsTracker.onHardCapReached();
            } else {
                int i3 = this.mPoolParams.maxSizeSoftCap;
                if (i > i3 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                    trimToSize(i3 - i);
                }
                if (i > i2 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                    this.mPoolStatsTracker.onHardCapReached();
                } else {
                    z = true;
                }
            }
        }
        return z;
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void logStats() {
        if (a.a(2)) {
            a.a(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    public synchronized Map<String, Integer> getStats() {
        Map<String, Integer> hashMap;
        hashMap = new HashMap();
        for (int i = 0; i < this.mBuckets.size(); i++) {
            hashMap.put(PoolStatsTracker.BUCKETS_USED_PREFIX + getSizeInBytes(this.mBuckets.keyAt(i)), Integer.valueOf(((Bucket) this.mBuckets.valueAt(i)).getInUseCount()));
        }
        hashMap.put(PoolStatsTracker.SOFT_CAP, Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
        hashMap.put(PoolStatsTracker.HARD_CAP, Integer.valueOf(this.mPoolParams.maxSizeHardCap));
        hashMap.put(PoolStatsTracker.USED_COUNT, Integer.valueOf(this.mUsed.mCount));
        hashMap.put(PoolStatsTracker.USED_BYTES, Integer.valueOf(this.mUsed.mNumBytes));
        hashMap.put(PoolStatsTracker.FREE_COUNT, Integer.valueOf(this.mFree.mCount));
        hashMap.put(PoolStatsTracker.FREE_BYTES, Integer.valueOf(this.mFree.mNumBytes));
        return hashMap;
    }
}
