package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ab;

final class bj extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ab b;

    bj(int i, XMPushService xMPushService, ab abVar) {
        this.a = xMPushService;
        this.b = abVar;
        super(i);
    }

    public void a() {
        try {
            ab a = s.a(this.a, this.b);
            a.m().a("miui_message_unrecognized", "1");
            j.a(this.a, a);
        } catch (Exception e) {
            b.a((Throwable) e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send ack message for unrecognized new miui message.";
    }
}
