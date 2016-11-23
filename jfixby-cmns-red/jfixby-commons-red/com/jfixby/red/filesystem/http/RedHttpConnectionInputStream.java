
package com.jfixby.red.filesystem.http;

import java.net.URLConnection;

import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class RedHttpConnectionInputStream extends AbstractRedInputStream<RedHttpConnectionInputStreamOperator>
	implements HttpConnectionInputStream {

	public RedHttpConnectionInputStream (final URLConnection java_connection) {
		super(new RedHttpConnectionInputStreamOperator(java_connection));
	}

}
