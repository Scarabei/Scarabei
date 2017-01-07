
package com.jfixby.scarabei.red.net.http;

import com.jfixby.scarabei.api.net.http.HttpConnectionOutputStream;
import com.jfixby.scarabei.red.io.AbstractRedOutputStream;

public class RedHttpConnectionOutputStream extends AbstractRedOutputStream<RedHttpConnectionOutputStreamOperator>
	implements HttpConnectionOutputStream {

	public RedHttpConnectionOutputStream (final RedHttpConnection java_connection) {
		super(new RedHttpConnectionOutputStreamOperator(java_connection));
	}

}
