package cn.v6.sixrooms.room.view;

import android.view.animation.Interpolator;

final class h implements Interpolator {
    h() {
    }

    public final float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
    }
}
