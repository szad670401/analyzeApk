package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.support.v7.widget.RecyclerView.SavedState;

final class bu implements ClassLoaderCreator<SavedState> {
    bu() {
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
