package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.internal.g;
import com.facebook.common.memory.c;
import com.facebook.imagepipeline.memory.BasePool.InvalidSizeException;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativeMemoryChunkPool extends BasePool<NativeMemoryChunk> {
    private final int[] mBucketSizes;

    public NativeMemoryChunkPool(c cVar, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(cVar, poolParams, poolStatsTracker);
        SparseIntArray sparseIntArray = poolParams.bucketSizes;
        this.mBucketSizes = new int[sparseIntArray.size()];
        for (int i = 0; i < this.mBucketSizes.length; i++) {
            this.mBucketSizes[i] = sparseIntArray.keyAt(i);
        }
        initialize();
    }

    public int getMinBufferSize() {
        return this.mBucketSizes[0];
    }

    protected NativeMemoryChunk alloc(int i) {
        return new NativeMemoryChunk(i);
    }

    protected void free(NativeMemoryChunk nativeMemoryChunk) {
        g.a((Object) nativeMemoryChunk);
        nativeMemoryChunk.close();
    }

    protected int getSizeInBytes(int i) {
        return i;
    }

    protected int getBucketedSize(int i) {
        if (i <= 0) {
            throw new InvalidSizeException(Integer.valueOf(i));
        }
        for (int i2 : this.mBucketSizes) {
            if (i2 >= i) {
                return i2;
            }
        }
        return i;
    }

    protected int getBucketedSizeForValue(NativeMemoryChunk nativeMemoryChunk) {
        g.a((Object) nativeMemoryChunk);
        return nativeMemoryChunk.getSize();
    }

    protected boolean isReusable(NativeMemoryChunk nativeMemoryChunk) {
        g.a((Object) nativeMemoryChunk);
        return !nativeMemoryChunk.isClosed();
    }
}
