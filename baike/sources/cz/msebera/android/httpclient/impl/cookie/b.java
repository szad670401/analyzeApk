package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;

class b extends BasicPathHandler {
    final /* synthetic */ DefaultCookieSpecProvider a;

    b(DefaultCookieSpecProvider defaultCookieSpecProvider) {
        this.a = defaultCookieSpecProvider;
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
    }
}
