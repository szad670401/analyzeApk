package com.umeng.analytics.pro;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

class bb extends SSLSocketFactory {
    SSLContext a = SSLContext.getInstance("TLS");

    public bb(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        try {
            AnonymousClass1 anonymousClass1 = new ao(this) {
                final /* synthetic */ bb b;

                {
                    this.b = r1;
                }
            };
            this.a.init(null, new TrustManager[]{anonymousClass1}, null);
        } catch (Throwable th) {
        }
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
        return this.a.getSocketFactory().createSocket(socket, str, i, z);
    }

    public Socket createSocket() throws IOException {
        return this.a.getSocketFactory().createSocket();
    }
}
