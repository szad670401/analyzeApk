package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.AddFollowEngine.CallBack;
import cn.v6.sixrooms.view.interfaces.FollowViewable;
import java.util.Iterator;

final class c implements CallBack {
    final /* synthetic */ FollowPresenter a;

    c(FollowPresenter followPresenter) {
        this.a = followPresenter;
    }

    public final void result(boolean z) {
        FollowPresenter.a(this.a, true);
        Iterator it = FollowPresenter.a(this.a).iterator();
        while (it.hasNext()) {
            ((FollowViewable) it.next()).updateFollow(FollowPresenter.b(this.a));
        }
    }

    public final void error(int i) {
        Iterator it = FollowPresenter.a(this.a).iterator();
        while (it.hasNext()) {
            ((FollowViewable) it.next()).updateFollowNetError(FollowPresenter.b(this.a), i);
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        Iterator it = FollowPresenter.a(this.a).iterator();
        while (it.hasNext()) {
            ((FollowViewable) it.next()).updateFollowServerError(FollowPresenter.b(this.a), str, str2);
        }
    }
}
