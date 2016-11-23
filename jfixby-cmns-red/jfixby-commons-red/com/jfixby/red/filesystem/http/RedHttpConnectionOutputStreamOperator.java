
package com.jfixby.red.filesystem.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.io.JavaOutputStreamOperator;
import com.jfixby.cmns.api.java.ByteArray;

public class RedHttpConnectionOutputStreamOperator implements JavaOutputStreamOperator {

	private OutputStream java_output_stream;
	private final URLConnection java_connection;

	public RedHttpConnectionOutputStreamOperator (final URLConnection java_connection) {
		Debug.checkNull("java_connection", java_connection);
		this.java_connection = java_connection;
	}

	@Override
	public void closeStream () {
		this.java_output_stream = null;
	}

	@Override
	public OutputStream getJavaStream () throws IOException {
		if (this.java_output_stream == null) {

			this.java_output_stream = this.java_connection.getOutputStream();
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
