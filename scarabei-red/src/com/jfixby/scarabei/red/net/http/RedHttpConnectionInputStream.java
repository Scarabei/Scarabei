
package com.jfixby.scarabei.red.net.http;

import java.net.URLConnection;

import com.jfixby.scarabei.api.net.http.HttpConnectionInputStream;
import com.jfixby.scarabei.red.io.AbstractRedInputStream;

public class RedHttpConnectionInputStream extends AbstractRedInputStream<RedHttpConnectionInputStreamOperator>
	implements HttpConnectionInputStream {

	public RedHttpConnectionInputStream (final URLConnection java_connection) {
		super(new RedHttpConnectionInputStreamOperator(java_connection));
	}

}
