package qsbk.app.fragments;

import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.ToastAndDialog$Lofter;

class ev extends BaseBitmapDataSubscriber {
    final /* synthetic */ SplashAdItem a;
    final /* synthetic */ LiveTabsFragment b;

    ev(LiveTabsFragment liveTabsFragment, SplashAdItem splashAdItem) {
        this.b = liveTabsFragment;
        this.a = splashAdItem;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        this.b.B.setVisibility(0);
        if (!SharePreferenceUtils.getSharePreferencesBoolValue("activity_notification_anim_" + this.a.id)) {
            SharePreferenceUtils.setSharePreferencesValue("activity_notification_anim_" + this.a.id, true);
            Animation loadAnimation = AnimationUtils.loadAnimation(this.b.B.getContext(), R.anim.activity_btn_scale);
            loadAnimation.setInterpolator(new ToastAndDialog$Lofter());
            this.b.B.startAnimation(loadAnimation);
        }
        FrescoImageloader.displayImage(this.b.B, this.a.picUrl);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}