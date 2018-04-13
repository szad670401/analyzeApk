package qsbk.app.fragments;

import android.support.v4.app.FragmentActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.QiuYouActivity;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.ToastAndDialog;

class fy implements HttpCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ MyGroupFragment b;

    fy(MyGroupFragment myGroupFragment, int i) {
        this.b = myGroupFragment;
        this.a = i;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        int i = 0;
        MyGroupFragment.a(this.b, null);
        if (this.b.getActivity() != null) {
            try {
                boolean z = jSONObject.getBoolean("has_more");
                int i2 = jSONObject.getInt("total");
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                if (this.a == 1) {
                    MyGroupFragment.a(this.b).clear();
                    this.b.a.refreshDone();
                    if (this.b.c != null) {
                        this.b.c.qiuYouNum(i2, this.b.d);
                    }
                } else {
                    this.b.a.loadMoreDone(true);
                }
                while (i < jSONArray.length()) {
                    MyGroupFragment.a(this.b).add(new GroupBriefInfo(jSONArray.getJSONObject(i)));
                    i++;
                }
                MyGroupFragment.a(this.b, this.a + 1);
                if (z) {
                    this.b.a.setLoadMoreEnable(true);
                } else {
                    this.b.a.setLoadMoreEnable(false);
                }
                if (MyGroupFragment.a(this.b).isEmpty()) {
                    MyGroupFragment.b(this.b);
                } else {
                    MyGroupFragment.c(this.b);
                }
                MyGroupFragment.d(this.b).notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onFailure(String str, String str2) {
        MyGroupFragment.a(this.b, null);
        if (this.b.getActivity() != null) {
            if (this.a == 1) {
                this.b.a.refreshDone();
            } else {
                this.b.a.loadMoreDone(false);
            }
            if (MyGroupFragment.a(this.b).isEmpty()) {
                MyGroupFragment.b(this.b);
            } else {
                MyGroupFragment.c(this.b);
            }
            FragmentActivity activity = this.b.getActivity();
            if (activity != null && (activity instanceof QiuYouActivity) && ((QiuYouActivity) activity).getCurrentItem() == 3) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
            }
        }
    }
}
