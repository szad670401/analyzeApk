package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;

class AppCompatBackgroundHelper {
    private int mBackgroundResId = -1;
    private TintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private TintInfo mInternalBackgroundTint;
    private TintInfo mTmpInfo;
    private final View mView;

    AppCompatBackgroundHelper(View view) {
        this.mView = view;
        this.mDrawableManager = AppCompatDrawableManager.get();
    }

    void loadFromAttributes(AttributeSet attributeSet, int i) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, R.styleable.ViewBackgroundHelper, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_android_background)) {
                this.mBackgroundResId = obtainStyledAttributes.getResourceId(R.styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList tintList = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId);
                if (tintList != null) {
                    setInternalBackgroundTint(tintList);
                }
            }
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.mView, obtainStyledAttributes.getColorStateList(R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    void onSetBackgroundResource(int i) {
        this.mBackgroundResId = i;
        setInternalBackgroundTint(this.mDrawableManager != null ? this.mDrawableManager.getTintList(this.mView.getContext(), i) : null);
        applySupportBackgroundTint();
    }

    void onSetBackgroundDrawable(Drawable drawable) {
        this.mBackgroundResId = -1;
        setInternalBackgroundTint(null);
        applySupportBackgroundTint();
    }

    void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        this.mBackgroundTint.mTintList = colorStateList;
        this.mBackgroundTint.mHasTintList = true;
        applySupportBackgroundTint();
    }

    ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTint != null ? this.mBackgroundTint.mTintList : null;
    }

    void setSupportBackgroundTintMode(Mode mode) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        this.mBackgroundTint.mTintMode = mode;
        this.mBackgroundTint.mHasTintMode = true;
        applySupportBackgroundTint();
    }

    Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTint != null ? this.mBackgroundTint.mTintMode : null;
    }

    void applySupportBackgroundTint() {
        Drawable background = this.mView.getBackground();
        if (background == null) {
            return;
        }
        if (!shouldApplyFrameworkTintUsingColorFilter() || !applyFrameworkTintUsingColorFilter(background)) {
            if (this.mBackgroundTint != null) {
                AppCompatDrawableManager.tintDrawable(background, this.mBackgroundTint, this.mView.getDrawableState());
            } else if (this.mInternalBackgroundTint != null) {
                AppCompatDrawableManager.tintDrawable(background, this.mInternalBackgroundTint, this.mView.getDrawableState());
            }
        }
    }

    void setInternalBackgroundTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new TintInfo();
            }
            this.mInternalBackgroundTint.mTintList = colorStateList;
            this.mInternalBackgroundTint.mHasTintList = true;
        } else {
            this.mInternalBackgroundTint = null;
        }
        applySupportBackgroundTint();
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter() {
        int i = VERSION.SDK_INT;
        if (i > 21) {
            if (this.mInternalBackgroundTint != null) {
                return true;
            }
            return false;
        } else if (i != 21) {
            return false;
        } else {
            return true;
        }
    }

    private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable drawable) {
        if (this.mTmpInfo == null) {
            this.mTmpInfo = new TintInfo();
        }
        TintInfo tintInfo = this.mTmpInfo;
        tintInfo.clear();
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(this.mView);
        if (backgroundTintList != null) {
            tintInfo.mHasTintList = true;
            tintInfo.mTintList = backgroundTintList;
        }
        Mode backgroundTintMode = ViewCompat.getBackgroundTintMode(this.mView);
        if (backgroundTintMode != null) {
            tintInfo.mHasTintMode = true;
            tintInfo.mTintMode = backgroundTintMode;
        }
        if (!tintInfo.mHasTintList && !tintInfo.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
        return true;
    }
}
