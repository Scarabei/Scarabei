
package com.jfixby.scarabei.red.net.http;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.io.JavaOutputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;

public class RedHttpConnectionOutputStreamOperator implements JavaOutputStreamOperator {

	private OutputStream java_output_stream;
	private final RedHttpConnection red_connection;

	public RedHttpConnectionOutputStreamOperator (final RedHttpConnection red_connection) {
		Debug.checkNull("red_connection", red_connection);
		this.red_connection = red_connection;

	}

	@Override
	public void closeStream () {
		this.java_output_stream = null;
	}

	@Override
	public OutputStream getJavaStream () throws IOException {
		if (this.red_connection.java_connection == null) {
			this.red_connection.tryToOpenConnection();
		}
		if (this.java_output_stream == null) {
			this.java_output_stream = this.red_connection.java_connection.getOutputStream();
		}
		return this.java_output_stream;
	}

	@Override
	public boolean isBulkWriteSupported () {
		return false;
	}

	@Override
	public void writeAll (final ByteArray bytes) throws IOException {
		Err.reportError("not supported");
	}

}
