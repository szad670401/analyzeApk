package qsbk.app.pay.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.pay.adapter.DiamondAdapter.ViewHolderBalance;

class b implements OnClickListener {
    final /* synthetic */ ViewHolderBalance a;
    final /* synthetic */ DiamondAdapter b;

    b(DiamondAdapter diamondAdapter, ViewHolderBalance viewHolderBalance) {
        this.b = diamondAdapter;
        this.a = viewHolderBalance;
    }

    public void onClick(View view) {
        this.b.a(this.a, 0, true);
    }
}
