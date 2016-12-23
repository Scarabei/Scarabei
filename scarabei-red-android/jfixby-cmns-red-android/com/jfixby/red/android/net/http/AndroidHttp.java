
package com.jfixby.red.android.net.http;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.red.android.net.http.sslfix.NoSSLv3SocketFactory;
import com.jfixby.red.net.http.RedHttp;

public class AndroidHttp extends RedHttp {
	public AndroidHttp () {
		try {

			final SSLContext sslcontext = SSLContext.getInstance("TLSv1");
			sslcontext.init(null, null, null);

			final SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());
			HttpsURLConnection.setDefaultSSLSocketFactory(NoSSLv3Factory);

		} catch (final Exception e) {
			e.printStackTrace();
			Err.reportError(e);
		}
	}
}
