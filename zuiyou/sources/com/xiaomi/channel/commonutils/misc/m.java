package com.xiaomi.channel.commonutils.misc;

import android.os.Looper;
import com.xiaomi.channel.commonutils.logger.b;

public class m {
    public static void a(boolean z) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread() && z) {
            throw new RuntimeException("can't do this on ui thread when debug_switch:" + z);
        } else if (Looper.getMainLooper().getThread() == Thread.currentThread() && !z) {
            b.a("can't do this on ui thread when debug_switch:" + z);
        }
    }
}
