package cn.v6.sixrooms.room.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

final class ad implements OnDismissListener {
    final /* synthetic */ FullScreenRoomFragment a;

    ad(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        FullScreenRoomFragment.C(this.a);
    }
}
