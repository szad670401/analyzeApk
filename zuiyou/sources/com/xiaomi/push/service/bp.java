package com.xiaomi.push.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.smack.util.e;

class bp extends BroadcastReceiver {
    final /* synthetic */ XMPushService a;

    bp(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals("com.xiaomi.metoknlp.geofencing.state_change_protected", intent.getAction())) {
            String stringExtra = intent.getStringExtra("Describe");
            String stringExtra2 = intent.getStringExtra(MNSConstants.SUBSCRIPTION_STATUS);
            if (!TextUtils.isEmpty(stringExtra)) {
                if (!this.a.a(stringExtra2, stringExtra, context)) {
                    stringExtra2 = "Unknown";
                    b.a(" updated geofence statue about geo_id:" + stringExtra + " falied. current_statue:" + stringExtra2);
                }
                e.a(new bq(this, context, stringExtra, stringExtra2));
                b.c("ownresilt结果:state= " + stringExtra2 + "\n describe=" + stringExtra);
            }
        }
    }
}
