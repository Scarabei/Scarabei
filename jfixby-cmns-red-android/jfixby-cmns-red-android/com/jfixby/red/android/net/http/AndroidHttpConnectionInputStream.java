
package com.jfixby.red.android.net.http;

import java.net.URLConnection;

import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class AndroidHttpConnectionInputStream extends AbstractRedInputStream<AndroidHttpConnectionInputStreamOperator>
	implements HttpConnectionInputStream {

	public AndroidHttpConnectionInputStream (final URLConnection java_connection) {
		super(new AndroidHttpConnectionInputStreamOperator(java_connection));
	}

}
