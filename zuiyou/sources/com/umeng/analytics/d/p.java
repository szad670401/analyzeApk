package com.umeng.analytics.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.umeng.a.d;
import com.umeng.a.e;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import com.umeng.analytics.b.b.c;
import com.umeng.analytics.b.f;
import com.umeng.analytics.b.g;
import java.lang.reflect.Method;
import org.json.JSONObject;

public class p {
    private static final String a = "session_start_time";
    private static final String b = "session_end_time";
    private static final String c = "session_id";
    private static String f = null;
    private static Context g = null;
    private final String d = "a_start_time";
    private final String e = "a_end_time";

    public boolean a(Context context) {
        SharedPreferences a = m.a(context);
        String string = a.getString("session_id", null);
        if (string == null) {
            return false;
        }
        long j = a.getLong(a, 0);
        long j2 = a.getLong(b, 0);
        if (j2 == 0 || Math.abs(j2 - j) > a.i) {
            try {
            } catch (Throwable th) {
                return false;
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(c.a.a, string);
        jSONObject.put("__e", j);
        jSONObject.put(c.a.g, j2);
        double[] location = AnalyticsConfig.getLocation();
        if (location != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(g.ae, location[0]);
            jSONObject2.put(g.af, location[1]);
            jSONObject2.put("ts", System.currentTimeMillis());
            jSONObject.put(c.a.e, jSONObject2);
        }
        Class cls = Class.forName("android.net.TrafficStats");
        Method method = cls.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
        Method method2 = cls.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
        if (context.getApplicationInfo().uid == -1) {
            return false;
        }
        long longValue = ((Long) method.invoke(null, new Object[]{Integer.valueOf(context.getApplicationInfo().uid)})).longValue();
        j = ((Long) method2.invoke(null, new Object[]{Integer.valueOf(r6)})).longValue();
        if (longValue > 0 && j > 0) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(g.aj, longValue);
            jSONObject3.put(g.ai, j);
            jSONObject.put(c.a.d, jSONObject3);
        }
        f.a(context).a(string, jSONObject, f.a.NEWSESSION);
        r.a(g);
        b.b(g);
        a(a);
        return true;
    }

    private void a(SharedPreferences sharedPreferences) {
        Editor edit = sharedPreferences.edit();
        edit.remove(a);
        edit.remove(b);
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.commit();
    }

    public String b(Context context) {
        String c = d.c(context);
        String appkey = AnalyticsConfig.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentTimeMillis).append(appkey).append(c);
        f = e.a(stringBuilder.toString());
        return f;
    }

    public void c(Context context) {
        g = context;
        SharedPreferences a = m.a(context);
        if (a != null) {
            Editor edit = a.edit();
            int i = a.getInt(a.B, 0);
            int parseInt = Integer.parseInt(d.a(g));
            if (i != 0 && parseInt != i) {
                try {
                    edit.putInt("vers_code", i);
                    edit.putString("vers_name", a.getString(a.C, ""));
                    edit.commit();
                } catch (Throwable th) {
                }
                if (g(context) == null) {
                    a(context, a);
                }
                e(g);
                d.b(g).b();
                f(g);
                d.b(g).a();
            } else if (b(a)) {
                com.umeng.a.g.b("Start new session: " + a(context, a));
            } else {
                String string = a.getString("session_id", null);
                edit.putLong("a_start_time", System.currentTimeMillis());
                edit.putLong("a_end_time", 0);
                edit.commit();
                com.umeng.a.g.b("Extend current session: " + string);
            }
        }
    }

    public void d(Context context) {
        SharedPreferences a = m.a(context);
        if (a != null) {
            if (a.getLong("a_start_time", 0) == 0 && AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                com.umeng.a.g.d("onPause called before onResume");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Editor edit = a.edit();
            edit.putLong("a_start_time", 0);
            edit.putLong("a_end_time", currentTimeMillis);
            edit.putLong(b, currentTimeMillis);
            edit.commit();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(c.a.g, currentTimeMillis);
                f.a(context).a(a(), jSONObject, f.a.END);
            } catch (Throwable th) {
            }
        }
    }

    private boolean b(SharedPreferences sharedPreferences) {
        long j = sharedPreferences.getLong("a_start_time", 0);
        long j2 = sharedPreferences.getLong("a_end_time", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (j != 0 && currentTimeMillis - j < AnalyticsConfig.kContinueSessionMillis) {
            com.umeng.a.g.d("onResume called before onPause");
            return false;
        } else if (currentTimeMillis - j2 > AnalyticsConfig.kContinueSessionMillis) {
            return true;
        } else {
            return false;
        }
    }

    private String a(Context context, SharedPreferences sharedPreferences) {
        d b = d.b(context);
        String b2 = b(context);
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__e", currentTimeMillis);
            f.a(g).a(b2, jSONObject, f.a.BEGIN);
        } catch (Throwable th) {
        }
        a(context);
        Editor edit = sharedPreferences.edit();
        edit.putString("session_id", b2);
        edit.putLong(a, System.currentTimeMillis());
        edit.putLong(b, 0);
        edit.putLong("a_start_time", currentTimeMillis);
        edit.putLong("a_end_time", 0);
        edit.putInt(a.B, Integer.parseInt(d.a(context)));
        edit.putString(a.C, d.b(context));
        edit.commit();
        b.a(Boolean.valueOf(true));
        return b2;
    }

    public boolean e(Context context) {
        boolean z = false;
        SharedPreferences a = m.a(context);
        if (!(a == null || a.getString("session_id", null) == null)) {
            long j = a.getLong("a_start_time", 0);
            long j2 = a.getLong("a_end_time", 0);
            if (j > 0 && j2 == 0) {
                z = true;
                d(context);
            }
            a(context);
        }
        return z;
    }

    public void f(Context context) {
        SharedPreferences a = m.a(context);
        if (a != null) {
            String b = b(context);
            Editor edit = a.edit();
            long currentTimeMillis = System.currentTimeMillis();
            edit.putString("session_id", b);
            edit.putLong(a, System.currentTimeMillis());
            edit.putLong(b, 0);
            edit.putLong("a_start_time", currentTimeMillis);
            edit.putLong("a_end_time", 0);
            edit.putInt(a.B, Integer.parseInt(d.a(context)));
            edit.putString(a.C, d.b(context));
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("__e", currentTimeMillis);
                f.a(g).a(b, jSONObject, f.a.BEGIN);
            } catch (Throwable th) {
            }
            edit.commit();
        }
    }

    public static String g(Context context) {
        if (f == null) {
            f = m.a(context).getString("session_id", null);
        }
        return f;
    }

    public static String a() {
        return g(g);
    }
}
