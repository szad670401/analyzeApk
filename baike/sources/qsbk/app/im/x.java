package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.LaiseeGetActivity;
import qsbk.app.model.Laisee;
import qsbk.app.model.LaiseeImInfo;
import qsbk.app.utils.Util;

class x implements OnClickListener {
    final /* synthetic */ LaiseeImInfo a;
    final /* synthetic */ j b;

    x(j jVar, LaiseeImInfo laiseeImInfo) {
        this.b = jVar;
        this.a = laiseeImInfo;
    }

    public void onClick(View view) {
        LaiseeGetActivity.launch(Util.getActivityOrContext(view), this.b.c.j, new Laisee(this.a.laiseeId, this.a.secret));
    }
}
