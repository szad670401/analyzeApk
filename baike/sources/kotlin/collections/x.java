package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "invoke"}, k = 3, mv = {1, 1, 6})
final class x extends Lambda implements Function0<Iterator<? extends T>> {
    final /* synthetic */ Iterable a;

    x(Iterable iterable) {
        this.a = iterable;
        super(0);
    }

    @NotNull
    public final Iterator<T> invoke() {
        return this.a.iterator();
    }
}
