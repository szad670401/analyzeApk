package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnLongClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.LoginPermissionClickDelegate;

class ca implements OnLongClickListener {
    final /* synthetic */ WebAdCell a;

    ca(WebAdCell webAdCell) {
        this.a = webAdCell;
    }

    public boolean onLongClick(View view) {
        if (QsbkApp.currentUser == null) {
            LoginPermissionClickDelegate.startLoginActivity(view.getContext());
        } else if (this.a.shareListener != null) {
            this.a.shareListener.onCircleShareStart((CircleArticle) this.a.getItem(), ShareUtils$OnCircleShareStartListener.TYPE_REPORT_OR_COPY, this.a.actionView);
        }
        return true;
    }
}