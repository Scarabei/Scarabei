
package com.jfixby.scarabei.red.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.io.JavaInputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;

public class RedHttpConnectionInputStreamOperator implements JavaInputStreamOperator {

	private InputStream java_input_stream;
	private final URLConnection java_connection;

	public RedHttpConnectionInputStreamOperator (final URLConnection java_connection) {
		this.java_connection = java_connection;
	}

	@Override
	public void closeStream () {
		this.java_input_stream = null;
	}

	@Override
	public InputStream getJavaStream () throws IOException {
		if (this.java_input_stream == null) {
			this.java_input_stream = this.java_connection.getInputStream();
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
