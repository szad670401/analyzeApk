package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.ImageViewer;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.UIHelper;

class af implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ ForwardCell b;

    af(ForwardCell forwardCell, CircleArticle circleArticle) {
        this.b = forwardCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        String imageUrl;
        String imageUrl2 = this.a.hotComment.smallImage.getImageUrl();
        if (this.a.hotComment.bigImage == null) {
            imageUrl = this.a.hotComment.smallImage.getImageUrl();
        } else {
            imageUrl = this.a.hotComment.bigImage.getImageUrl();
        }
        ImageViewer.launch(this.b.getContext(), new ImageInfo(imageUrl2, imageUrl), UIHelper.getRectOnScreen(this.b.hotCommentImage));
    }
}