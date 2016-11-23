
package com.jfixby.red.filesystem.http;

import java.net.URLConnection;

import com.jfixby.cmns.api.net.http.HttpConnectionOutputStream;
import com.jfixby.red.io.AbstractRedOutputStream;

public class RedHttpConnectionOutputStream extends AbstractRedOutputStream<RedHttpConnectionOutputStreamOperator>
	implements HttpConnectionOutputStream {

	public RedHttpConnectionOutputStream (final URLConnection java_connection) {
		super(new RedHttpConnectionOutputStreamOperator(java_connection));
	}

}
