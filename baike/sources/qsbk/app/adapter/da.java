package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.model.BaseUserInfo;

class da implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ String b;
    final /* synthetic */ QiuYouAdapter c;

    da(QiuYouAdapter qiuYouAdapter, BaseUserInfo baseUserInfo, String str) {
        this.c = qiuYouAdapter;
        this.a = baseUserInfo;
        this.b = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = Constants.REL_UNFOLLOW;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        str = String.format(str, objArr);
        Map hashMap = new HashMap();
        hashMap.put("uid", this.a.userId);
        this.c.a(this.a.userId, str, hashMap, this.b);
        dialogInterface.cancel();
    }
}
