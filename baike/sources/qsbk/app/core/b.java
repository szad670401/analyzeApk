package qsbk.app.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class b implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    b() {
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "qsbk-AsyncTask #" + this.a.getAndIncrement());
    }
}
