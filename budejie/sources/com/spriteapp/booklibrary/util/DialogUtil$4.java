package com.spriteapp.booklibrary.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class DialogUtil$4 implements OnClickListener {
    DialogUtil$4() {
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (DialogUtil.access$000() != null) {
            DialogUtil.access$000().clickSetting();
        }
    }
}
