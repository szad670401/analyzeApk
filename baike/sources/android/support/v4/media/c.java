package android.support.v4.media;

import android.support.v4.media.MediaBrowserCompat.ItemCallback;

class c implements Runnable {
    final /* synthetic */ ItemCallback a;
    final /* synthetic */ String b;
    final /* synthetic */ c c;

    c(c cVar, ItemCallback itemCallback, String str) {
        this.c = cVar;
        this.a = itemCallback;
        this.b = str;
    }

    public void run() {
        this.a.onError(this.b);
    }
}
