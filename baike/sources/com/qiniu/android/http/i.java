package com.qiniu.android.http;

import com.qiniu.android.utils.StringMap.Consumer;
import okhttp3.Request$Builder;

class i implements Consumer {
    final /* synthetic */ Request$Builder a;
    final /* synthetic */ Client b;

    i(Client client, Request$Builder request$Builder) {
        this.b = client;
        this.a = request$Builder;
    }

    public void accept(String str, Object obj) {
        this.a.header(str, obj.toString());
    }
}
