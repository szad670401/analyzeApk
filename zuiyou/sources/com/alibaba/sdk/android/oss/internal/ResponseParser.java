package com.alibaba.sdk.android.oss.internal;

import java.io.IOException;
import okhttp3.aa;

public interface ResponseParser<T> {
    T parse(aa aaVar) throws IOException;
}