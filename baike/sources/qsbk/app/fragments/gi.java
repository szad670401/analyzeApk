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

class gi extends BaseBitmapDataSubscriber {
    final /* synthetic */ SplashAdItem a;
    final /* synthetic */ MyProfileFragment b;

    gi(MyProfileFragment myProfileFragment, SplashAdItem splashAdItem) {
        this.b = myProfileFragment;
        this.a = splashAdItem;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        this.b.s.setVisibility(0);
        this.b.s.getLayoutParams();
        if (!SharePreferenceUtils.getSharePreferencesBoolValue("activity_notification_anim_" + this.a.id)) {
            SharePreferenceUtils.setSharePreferencesValue("activity_notification_anim_" + this.a.id, true);
            Animation loadAnimation = AnimationUtils.loadAnimation(this.b.s.getContext(), R.anim.activity_btn_scale);
            loadAnimation.setInterpolator(new ToastAndDialog$Lofter());
            this.b.s.startAnimation(loadAnimation);
        }
        FrescoImageloader.displayImage(this.b.s, this.a.picUrl);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
