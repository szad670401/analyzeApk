package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;

final class an implements OnClickListener {
    final /* synthetic */ MobilePayActivity a;

    an(MobilePayActivity mobilePayActivity) {
        this.a = mobilePayActivity;
    }

    public final void onClick(View view) {
        this.a.makeOrder();
    }
}
