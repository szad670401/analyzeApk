package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class t implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    t(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
