package qsbk.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;

class ax implements OnClickListener {
    final /* synthetic */ aw a;

    ax(aw awVar) {
        this.a = awVar;
    }

    public void onClick(View view) {
        Context activityOrContext = Util.getActivityOrContext(view);
        if (activityOrContext instanceof CircleArticleActivity) {
            ((CircleArticleActivity) activityOrContext).showKeyboard();
            return;
        }
        CircleArticleActivity.launch(view.getContext(), (CircleArticle) this.a.getItem(), true, true, this.a.fromCircleTopic);
    }
}
