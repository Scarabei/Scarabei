
package com.jfixby.red.desktop.net.http;

import java.net.URLConnection;

import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class DesktopHttpConnectionInputStream extends AbstractRedInputStream<DesktopHttpConnectionInputStreamOperator>
	implements HttpConnectionInputStream {

	public DesktopHttpConnectionInputStream (final URLConnection java_connection) {
		super(new DesktopHttpConnectionInputStreamOperator(java_connection));
	}

}
