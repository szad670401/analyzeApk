package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ft implements OnClickListener {
    final /* synthetic */ ManangeMyContentsFragment a;

    ft(ManangeMyContentsFragment manangeMyContentsFragment) {
        this.a = manangeMyContentsFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.G();
        dialogInterface.dismiss();
    }
}
