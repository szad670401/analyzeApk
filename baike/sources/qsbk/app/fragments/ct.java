package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.utils.ListUtil;
import qsbk.app.widget.BaseCell;

class ct implements OnItemClickListener {
    final /* synthetic */ HotCommentCircleFragment a;

    ct(HotCommentCircleFragment hotCommentCircleFragment) {
        this.a = hotCommentCircleFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i >= ListUtil.getHeaderCount(this.a.g)) {
            ((BaseCell) view.getTag()).onClick();
        }
    }
}
