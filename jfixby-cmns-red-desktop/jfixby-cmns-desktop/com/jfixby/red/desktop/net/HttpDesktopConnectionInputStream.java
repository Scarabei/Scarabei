
package com.jfixby.red.desktop.net;

import java.net.URLConnection;

import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class HttpDesktopConnectionInputStream extends AbstractRedInputStream<HttpDesktopConnectionInputStreamOperator>
	implements HttpConnectionInputStream {

	public HttpDesktopConnectionInputStream (final URLConnection java_connection) {
		super(new HttpDesktopConnectionInputStreamOperator(java_connection));
	}

}
