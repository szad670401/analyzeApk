package com.xiaomi.channel.commonutils.logger;

import android.util.Log;

public class a implements c {
    private String a = "xiaomi";

    public void log(String str) {
        Log.v(this.a, str);
    }

    public void log(String str, Throwable th) {
        Log.v(this.a, str, th);
    }
}
