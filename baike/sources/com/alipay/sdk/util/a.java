package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.baidu.mobstat.Config;

public final class a {
    private static a d = null;
    public String a;
    private String b;
    private String c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private a(android.content.Context r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0052 in list [B:8:0x004e]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r3 = this;
        r3.<init>();
        r0 = "phone";	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r4.getSystemService(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r1 = r0.getDeviceId();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r3.a(r1);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.getSubscriberId();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        if (r0 == 0) goto L_0x0032;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
    L_0x0018:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r1.<init>();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r1.append(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r1 = "000000000000000";	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r1 = 0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r2 = 15;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.substring(r1, r2);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
    L_0x0032:
        r3.b = r0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = "wifi";	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r4.getSystemService(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.getConnectionInfo();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.getMacAddress();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r3.a = r0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r3.a;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0052;
    L_0x004e:
        r0 = "00:00:00:00:00:00";
        r3.a = r0;
    L_0x0052:
        return;
    L_0x0053:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r3.a;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0052;
    L_0x005f:
        r0 = "00:00:00:00:00:00";
        r3.a = r0;
        goto L_0x0052;
    L_0x0064:
        r0 = move-exception;
        r1 = r3.a;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 == 0) goto L_0x0071;
    L_0x006d:
        r1 = "00:00:00:00:00:00";
        r3.a = r1;
    L_0x0071:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.a.<init>(android.content.Context):void");
    }

    public static a a(Context context) {
        if (d == null) {
            d = new a(context);
        }
        return d;
    }

    public final String a() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = Config.NULL_DEVICE_ID;
        }
        return this.b;
    }

    public final String b() {
        if (TextUtils.isEmpty(this.c)) {
            this.c = Config.NULL_DEVICE_ID;
        }
        return this.c;
    }

    private void a(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            int i = 0;
            while (i < bytes.length) {
                if (bytes[i] < (byte) 48 || bytes[i] > (byte) 57) {
                    bytes[i] = (byte) 48;
                }
                i++;
            }
            str = (new String(bytes) + Config.NULL_DEVICE_ID).substring(0, 15);
        }
        this.c = str;
    }

    public static d b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return d.a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return d.NONE;
            }
            return d.a;
        } catch (Exception e) {
            return d.NONE;
        }
    }

    public static String c(Context context) {
        a a = a(context);
        String str = a.b() + "|";
        Object a2 = a.a();
        return (TextUtils.isEmpty(a2) ? str + Config.NULL_DEVICE_ID : str + a2).substring(0, 8);
    }

    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable th) {
            return "";
        }
    }
}
