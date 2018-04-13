package io.reactivex.internal.operators.maybe;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class MaybeConcatIterable<T> extends Flowable<T> {
    final Iterable<? extends MaybeSource<? extends T>> sources;

    static final class ConcatMaybeObserver<T> extends AtomicInteger implements MaybeObserver<T>, d {
        private static final long serialVersionUID = 3520831347801429610L;
        final c<? super T> actual;
        final AtomicReference<Object> current = new AtomicReference(NotificationLite.COMPLETE);
        final SequentialDisposable disposables = new SequentialDisposable();
        long produced;
        final AtomicLong requested = new AtomicLong();
        final Iterator<? extends MaybeSource<? extends T>> sources;

        ConcatMaybeObserver(c<? super T> cVar, Iterator<? extends MaybeSource<? extends T>> it) {
            this.actual = cVar;
            this.sources = it;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.disposables.dispose();
        }

        public void onSubscribe(Disposable disposable) {
            this.disposables.replace(disposable);
        }

        public void onSuccess(T t) {
            this.current.lazySet(t);
            drain();
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.current.lazySet(NotificationLite.COMPLETE);
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                AtomicReference atomicReference = this.current;
                c cVar = this.actual;
                Disposable disposable = this.disposables;
                while (!disposable.isDisposed()) {
                    NotificationLite notificationLite = atomicReference.get();
                    if (notificationLite != null) {
                        Object obj;
                        if (notificationLite != NotificationLite.COMPLETE) {
                            long j = this.produced;
                            if (j != this.requested.get()) {
                                this.produced = j + 1;
                                atomicReference.lazySet(null);
                                cVar.onNext(notificationLite);
                                obj = 1;
                            } else {
                                obj = null;
                            }
                        } else {
                            atomicReference.lazySet(null);
                            int i = 1;
                        }
                        if (!(obj == null || disposable.isDisposed())) {
                            try {
                                if (this.sources.hasNext()) {
                                    try {
                                        ((MaybeSource) ObjectHelper.requireNonNull(this.sources.next(), "The source Iterator returned a null MaybeSource")).subscribe(this);
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        cVar.onError(th);
                                        return;
                                    }
                                }
                                cVar.onComplete();
                            } catch (Throwable th2) {
                                Exceptions.throwIfFatal(th2);
                                cVar.onError(th2);
                                return;
                            }
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                atomicReference.lazySet(null);
            }
        }
    }

    public MaybeConcatIterable(Iterable<? extends MaybeSource<? extends T>> iterable) {
        this.sources = iterable;
    }

    protected void subscribeActual(c<? super T> cVar) {
        try {
            Object concatMaybeObserver = new ConcatMaybeObserver(cVar, (Iterator) ObjectHelper.requireNonNull(this.sources.iterator(), "The sources Iterable returned a null Iterator"));
            cVar.onSubscribe(concatMaybeObserver);
            concatMaybeObserver.drain();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, cVar);
        }
    }
}