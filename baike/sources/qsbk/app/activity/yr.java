package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;

class yr implements OnClickListener {
    final /* synthetic */ yo a;

    yr(yo yoVar) {
        this.a = yoVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = Constants.REL_UNFOLLOW;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        str = String.format(str, objArr);
        Map hashMap = new HashMap();
        hashMap.put("uid", OtherInfoActivity.d(this.a.a));
        OtherInfoActivity.a(this.a.a, Constants.REL_UNFOLLOW, str, hashMap);
        dialogInterface.cancel();
    }
}
