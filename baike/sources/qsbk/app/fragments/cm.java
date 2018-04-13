package qsbk.app.fragments;

import android.view.View;
import android.widget.ListView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.widget.qiuyoucircle.NormalCell;

class cm extends VideoInListHelper {
    final /* synthetic */ FollowCircleFragment a;

    cm(FollowCircleFragment followCircleFragment, ListView listView) {
        this.a = followCircleFragment;
        super(listView);
    }

    public Map<String, Object> getVideoFromItemView(View view) {
        Map hashMap = new HashMap();
        Object tag = view.getTag();
        if (tag != null && (tag instanceof NormalCell)) {
            NormalCell normalCell = (NormalCell) tag;
            hashMap.put(VideoInListHelper.VIEW, normalCell.playerView);
            hashMap.put(VideoInListHelper.TAG, Integer.valueOf(1));
            hashMap.put("article", normalCell.circleArticle);
        }
        return hashMap;
    }
}
