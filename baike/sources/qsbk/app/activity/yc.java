package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.Article;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class yc implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ NewImageViewer b;

    yc(NewImageViewer newImageViewer, Article article) {
        this.b = newImageViewer;
        this.a = article;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            if (HttpUtils.isNetworkConnected(this.b.getApplicationContext())) {
                ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "正在删除，请稍候...", Integer.valueOf(0)).show();
                this.b.a(this.a);
                return;
            }
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, this.b.getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
        } else if (i == 1) {
            dialogInterface.dismiss();
        }
    }
}
