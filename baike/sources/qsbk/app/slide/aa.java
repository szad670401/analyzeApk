package qsbk.app.slide;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobstat.Config;
import qsbk.app.utils.QiushiArticleBus;

class aa implements OnClickListener {
    final /* synthetic */ z a;

    aa(z zVar) {
        this.a = zVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.b.a(Config.DEVICE_NAME, this.a.a.id, "active");
                break;
            case 1:
                this.a.b.g(this.a.a);
                break;
            case 2:
                this.a.b.h(this.a.a);
                break;
        }
        QiushiArticleBus.onArticleDeleted(this.a.a);
        this.a.b.getActivity().finish();
    }
}