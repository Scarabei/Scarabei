
package com.jfixby.scarabei.red.net.http;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.io.JavaInputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;

public class RedHttpConnectionInputStreamOperator implements JavaInputStreamOperator {

	private InputStream java_input_stream;

	private final RedHttpConnection red_connection;

	public RedHttpConnectionInputStreamOperator (final RedHttpConnection red_connection) {
		Debug.checkNull("red_connection", red_connection);
		this.red_connection = red_connection;
	}

	@Override
	public void closeStream () {
		this.java_input_stream = null;
	}

	@Override
	public InputStream getJavaStream () throws IOException {
		if (this.red_connection.java_connection == null) {
			this.red_connection.tryToOpenConnection();
		}
		if (this.java_input_stream == null) {
			this.java_input_stream = this.red_connection.java_connection.getInputStream();
		}
		return this.java_input_stream;
	}

	@Override
	public boolean isReadAllSupported () {
		return false;
	}

	@Override
	public ByteArray readAll () throws IOException {
		Err.throwNotImplementedYet();
		return null;
	}

}
