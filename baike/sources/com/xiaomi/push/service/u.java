package com.xiaomi.push.service;

import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.g;

/* synthetic */ class u {
    static final /* synthetic */ int[] a = new int[f.values().length];
    static final /* synthetic */ int[] b = new int[g.values().length];

    static {
        try {
            b[g.INT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            b[g.LONG.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            b[g.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            b[g.BOOLEAN.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[f.MISC_CONFIG.ordinal()] = 1;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[f.PLUGIN_CONFIG.ordinal()] = 2;
        } catch (NoSuchFieldError e6) {
        }
    }
}
