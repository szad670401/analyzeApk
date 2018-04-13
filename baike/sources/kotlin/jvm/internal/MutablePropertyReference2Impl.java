package kotlin.jvm.internal;

import kotlin.reflect.KDeclarationContainer;

public class MutablePropertyReference2Impl extends MutablePropertyReference2 {
    private final KDeclarationContainer b;
    private final String c;
    private final String d;

    public MutablePropertyReference2Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        this.b = kDeclarationContainer;
        this.c = str;
        this.d = str2;
    }

    public KDeclarationContainer getOwner() {
        return this.b;
    }

    public String getName() {
        return this.c;
    }

    public String getSignature() {
        return this.d;
    }

    public Object get(Object obj, Object obj2) {
        return getGetter().call(new Object[]{obj, obj2});
    }

    public void set(Object obj, Object obj2, Object obj3) {
        getSetter().call(new Object[]{obj, obj2, obj3});
    }
}
