package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.misc.a;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.smack.b;

class bb extends h {
    final /* synthetic */ int a;
    final /* synthetic */ byte[] b;
    final /* synthetic */ String c;
    final /* synthetic */ XMPushService d;

    bb(XMPushService xMPushService, int i, int i2, byte[] bArr, String str) {
        this.d = xMPushService;
        this.a = i2;
        this.b = bArr;
        this.c = str;
        super(i);
    }

    public void a() {
        o.b(this.d);
        ak.a().a("5");
        a.a(this.a);
        this.d.c.b(b.b());
        this.d.a(this.b, this.c);
    }

    public String b() {
        return "clear account cache.";
    }
}
