package cn.v6.sixrooms.pay.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.pay.bean.OrderStatusBean;
import cn.v6.sixrooms.utils.UrlUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class OrderStatusEngine {
    protected static final String TAG = "OrderStatusEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleResult(OrderStatusBean orderStatusBean);
    }

    public OrderStatusEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void orderStatus(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("encpass", str2);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str3);
        arrayList.add(new BasicNameValuePair("orderid", str));
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new d(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-payOrderstatus.php"), arrayList);
    }

    public void orderStatusV2(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("encpass", str2);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str3);
        arrayList.add(new BasicNameValuePair("orderid", str));
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new e(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-payOrderstatusV2.php"), arrayList);
    }
}
