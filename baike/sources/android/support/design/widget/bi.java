package android.support.design.widget;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;

final class bi implements ClassLoaderCreator<SavedState> {
    bi() {
    }

    public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new SavedState(parcel, classLoader);
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel, null);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
