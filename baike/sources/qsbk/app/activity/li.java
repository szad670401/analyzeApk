package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class li implements OnClickListener {
    final /* synthetic */ FinishGroupActivity a;

    li(FinishGroupActivity finishGroupActivity) {
        this.a = finishGroupActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.f();
    }
}
