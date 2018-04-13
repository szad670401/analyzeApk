package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.reflect.a;
import java.security.MessageDigest;

public class h {
    private static Context a;

    public static Context a() {
        return a;
    }

    public static void a(Context context) {
        a = context.getApplicationContext();
    }

    public static String b() {
        String c = d.c(a);
        if (c == null) {
            c = d.i(a);
        }
        if (c != null) {
            try {
                return Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(c.getBytes()), 8).substring(0, 16);
            } catch (Throwable e) {
                b.a(e);
            }
        }
        return null;
    }

    public static boolean b(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Throwable e) {
            b.a(e);
            return false;
        }
    }

    public static int c() {
        try {
            Class cls = Class.forName("miui.os.Build");
            return cls.getField("IS_STABLE_VERSION").getBoolean(null) ? 3 : cls.getField("IS_DEVELOPMENT_VERSION").getBoolean(null) ? 2 : 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean d() {
        return TextUtils.equals((String) a.a("android.os.SystemProperties", "get", "sys.boot_completed"), "1");
    }

    public static boolean e() {
        boolean z = false;
        try {
            z = Class.forName("miui.os.Build").getField("IS_GLOBAL_BUILD").getBoolean(Boolean.valueOf(false));
        } catch (Throwable e) {
            b.a(e);
        }
        return z;
    }
}
