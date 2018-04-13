package io.reactivex.internal.subscriptions;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public final class AsyncSubscription extends AtomicLong implements Disposable, d {
    private static final long serialVersionUID = 7028635084060361255L;
    final AtomicReference<d> actual;
    final AtomicReference<Disposable> resource;

    public AsyncSubscription() {
        this.resource = new AtomicReference();
        this.actual = new AtomicReference();
    }

    public AsyncSubscription(Disposable disposable) {
        this();
        this.resource.lazySet(disposable);
    }

    public void request(long j) {
        SubscriptionHelper.deferredRequest(this.actual, this, j);
    }

    public void cancel() {
        dispose();
    }

    public void dispose() {
        SubscriptionHelper.cancel(this.actual);
        DisposableHelper.dispose(this.resource);
    }

    public boolean isDisposed() {
        return this.actual.get() == SubscriptionHelper.CANCELLED;
    }

    public boolean setResource(Disposable disposable) {
        return DisposableHelper.set(this.resource, disposable);
    }

    public boolean replaceResource(Disposable disposable) {
        return DisposableHelper.replace(this.resource, disposable);
    }

    public void setSubscription(d dVar) {
        SubscriptionHelper.deferredSetOnce(this.actual, this, dVar);
    }
}
