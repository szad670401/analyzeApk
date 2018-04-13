package com.xiaomi.xmpush.thrift;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.umeng.analytics.b.g;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;
import org.mozilla.classfile.ByteCode;

public class l implements Serializable, Cloneable, org.apache.thrift.a<l, a> {
    public static final Map<a, b> e;
    private static final j f = new j("GPS");
    private static final org.apache.thrift.protocol.b g = new org.apache.thrift.protocol.b(RequestParameters.SUBRESOURCE_LOCATION, (byte) 12, (short) 1);
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b(g.as, ByteCode.T_LONG, (short) 2);
    private static final org.apache.thrift.protocol.b i = new org.apache.thrift.protocol.b("period", (byte) 10, (short) 3);
    private static final org.apache.thrift.protocol.b j = new org.apache.thrift.protocol.b("accuracy", (byte) 4, (short) 4);
    public o a;
    public String b;
    public long c;
    public double d;
    private BitSet k = new BitSet(2);

    public enum a {
        LOCATION((short) 1, RequestParameters.SUBRESOURCE_LOCATION),
        PROVIDER((short) 2, g.as),
        PERIOD((short) 3, "period"),
        ACCURACY((short) 4, "accuracy");
        
        private static final Map<String, a> e = null;
        private final short f;
        private final String g;

        static {
            e = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                e.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.f = s;
            this.g = str;
        }

        public String a() {
            return this.g;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.LOCATION, new b(RequestParameters.SUBRESOURCE_LOCATION, (byte) 1, new org.apache.thrift.meta_data.g((byte) 12, o.class)));
        enumMap.put(a.PROVIDER, new b(g.as, (byte) 2, new c(ByteCode.T_LONG)));
        enumMap.put(a.PERIOD, new b("period", (byte) 2, new c((byte) 10)));
        enumMap.put(a.ACCURACY, new b("accuracy", (byte) 2, new c((byte) 4)));
        e = Collections.unmodifiableMap(enumMap);
        b.a(l.class, e);
    }

    public l a(double d) {
        this.d = d;
        b(true);
        return this;
    }

    public l a(long j) {
        this.c = j;
        a(true);
        return this;
    }

    public l a(o oVar) {
        this.a = oVar;
        return this;
    }

    public l a(String str) {
        this.b = str;
        return this;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                e();
                return;
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 12) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.a = new o();
                    this.a.a(eVar);
                    break;
                case (short) 2:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.b = eVar.w();
                        break;
                    }
                case (short) 3:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.c = eVar.u();
                    a(true);
                    break;
                case (short) 4:
                    if (i.b != (byte) 4) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.d = eVar.v();
                    b(true);
                    break;
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.k.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(l lVar) {
        if (lVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = lVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.a(lVar.a))) {
            return false;
        }
        a = b();
        a2 = lVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.equals(lVar.b))) {
            return false;
        }
        a = c();
        a2 = lVar.c();
        if ((a || a2) && (!a || !a2 || this.c != lVar.c)) {
            return false;
        }
        a = d();
        a2 = lVar.d();
        return !(a || a2) || (a && a2 && this.d == lVar.d);
    }

    public int b(l lVar) {
        if (!getClass().equals(lVar.getClass())) {
            return getClass().getName().compareTo(lVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(lVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, lVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(lVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, lVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(lVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, lVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(lVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.d, lVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        e();
        eVar.a(f);
        if (this.a != null) {
            eVar.a(g);
            this.a.b(eVar);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(h);
            eVar.a(this.b);
            eVar.b();
        }
        if (c()) {
            eVar.a(i);
            eVar.a(this.c);
            eVar.b();
        }
        if (d()) {
            eVar.a(j);
            eVar.a(this.d);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.k.set(1, z);
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean c() {
        return this.k.get(0);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((l) obj);
    }

    public boolean d() {
        return this.k.get(1);
    }

    public void e() {
        if (this.a == null) {
            throw new f("Required field 'location' was not present! Struct: " + toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof l)) ? a((l) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("GPS(");
        stringBuilder.append("location:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("provider:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("period:");
            stringBuilder.append(this.c);
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("accuracy:");
            stringBuilder.append(this.d);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
