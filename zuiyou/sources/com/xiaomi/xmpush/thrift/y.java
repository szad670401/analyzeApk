package com.xiaomi.xmpush.thrift;

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

public class y implements Serializable, Cloneable, org.apache.thrift.a<y, a> {
    public static final Map<a, b> d;
    private static final j e = new j("Wifi");
    private static final org.apache.thrift.protocol.b f = new org.apache.thrift.protocol.b("macAddress", ByteCode.T_LONG, (short) 1);
    private static final org.apache.thrift.protocol.b g = new org.apache.thrift.protocol.b("signalStrength", (byte) 8, (short) 2);
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b("ssid", ByteCode.T_LONG, (short) 3);
    public String a;
    public int b;
    public String c;
    private BitSet i = new BitSet(1);

    public enum a {
        MAC_ADDRESS((short) 1, "macAddress"),
        SIGNAL_STRENGTH((short) 2, "signalStrength"),
        SSID((short) 3, "ssid");
        
        private static final Map<String, a> d = null;
        private final short e;
        private final String f;

        static {
            d = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                d.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public String a() {
            return this.f;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.MAC_ADDRESS, new b("macAddress", (byte) 1, new c(ByteCode.T_LONG)));
        enumMap.put(a.SIGNAL_STRENGTH, new b("signalStrength", (byte) 1, new c((byte) 8)));
        enumMap.put(a.SSID, new b("ssid", (byte) 2, new c(ByteCode.T_LONG)));
        d = Collections.unmodifiableMap(enumMap);
        b.a(y.class, d);
    }

    public y a(int i) {
        this.b = i;
        a(true);
        return this;
    }

    public y a(String str) {
        this.a = str;
        return this;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (b()) {
                    d();
                    return;
                }
                throw new f("Required field 'signalStrength' was not found in serialized data! Struct: " + toString());
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.a = eVar.w();
                        break;
                    }
                case (short) 2:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = eVar.t();
                    a(true);
                    break;
                case (short) 3:
                    if (i.b != ByteCode.T_LONG) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = eVar.w();
                        break;
                    }
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.i.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(y yVar) {
        if (yVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = yVar.a();
        if (((a || a2) && (!a || !a2 || !this.a.equals(yVar.a))) || this.b != yVar.b) {
            return false;
        }
        a = c();
        a2 = yVar.c();
        return !(a || a2) || (a && a2 && this.c.equals(yVar.c));
    }

    public int b(y yVar) {
        if (!getClass().equals(yVar.getClass())) {
            return getClass().getName().compareTo(yVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(yVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, yVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(yVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, yVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(yVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, yVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public y b(String str) {
        this.c = str;
        return this;
    }

    public void b(e eVar) {
        d();
        eVar.a(e);
        if (this.a != null) {
            eVar.a(f);
            eVar.a(this.a);
            eVar.b();
        }
        eVar.a(g);
        eVar.a(this.b);
        eVar.b();
        if (this.c != null && c()) {
            eVar.a(h);
            eVar.a(this.c);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.i.get(0);
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((y) obj);
    }

    public void d() {
        if (this.a == null) {
            throw new f("Required field 'macAddress' was not present! Struct: " + toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof y)) ? a((y) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Wifi(");
        stringBuilder.append("macAddress:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("signalStrength:");
        stringBuilder.append(this.b);
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("ssid:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
