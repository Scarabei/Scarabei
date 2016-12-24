
package com.jfixby.scarabei.red.net.http;

import java.net.URLConnection;

import com.jfixby.scarabei.api.net.http.HttpConnectionOutputStream;
import com.jfixby.scarabei.red.io.AbstractRedOutputStream;

public class RedHttpConnectionOutputStream extends AbstractRedOutputStream<RedHttpConnectionOutputStreamOperator>
	implements HttpConnectionOutputStream {

	public RedHttpConnectionOutputStream (final URLConnection java_connection) {
		super(new RedHttpConnectionOutputStreamOperator(java_connection));
	}

}
