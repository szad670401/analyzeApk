package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import java.io.UnsupportedEncodingException;

public abstract class JsonRequest<T> extends Request<T> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[]{"utf-8"});
    private final Listener<T> mListener;
    private final String mRequestBody;

    protected abstract Response<T> parseNetworkResponse(NetworkResponse networkResponse);

    public JsonRequest(String str, String str2, Listener<T> listener, ErrorListener errorListener) {
        this(-1, str, str2, listener, errorListener);
    }

    public JsonRequest(int i, String str, String str2, Listener<T> listener, ErrorListener errorListener) {
        super(i, str, errorListener);
        this.mListener = listener;
        this.mRequestBody = str2;
    }

    protected void deliverResponse(T t) {
        this.mListener.onResponse(t);
    }

    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    public byte[] getPostBody() {
        return getBody();
    }

    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public byte[] getBody() {
        byte[] bArr = null;
        try {
            if (this.mRequestBody != null) {
                bArr = this.mRequestBody.getBytes("utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", this.mRequestBody, "utf-8");
        }
        return bArr;
    }
}
