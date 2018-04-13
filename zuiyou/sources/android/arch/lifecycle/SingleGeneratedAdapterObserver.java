package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final c a;

    public void a(g gVar, Event event) {
        this.a.a(gVar, event, false, null);
        this.a.a(gVar, event, true, null);
    }
}
