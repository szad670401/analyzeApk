package io.reactivex.internal.util;

import io.reactivex.CompletableObserver;
import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import org.a.c;
import org.a.d;

public enum EmptyComponent implements CompletableObserver, FlowableSubscriber<Object>, MaybeObserver<Object>, Observer<Object>, SingleObserver<Object>, Disposable, d {
    INSTANCE;

    public static <T> c<T> asSubscriber() {
        return INSTANCE;
    }

    public static <T> Observer<T> asObserver() {
        return INSTANCE;
    }

    public void dispose() {
    }

    public boolean isDisposed() {
        return true;
    }

    public void request(long j) {
    }

    public void cancel() {
    }

    public void onSubscribe(Disposable disposable) {
        disposable.dispose();
    }

    public void onSubscribe(d dVar) {
        dVar.cancel();
    }

    public void onNext(Object obj) {
    }

    public void onError(Throwable th) {
        RxJavaPlugins.onError(th);
    }

    public void onComplete() {
    }

    public void onSuccess(Object obj) {
    }
}
