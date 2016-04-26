
package com.jfixby.red.io;

import java.io.InputStream;

import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.JavaInputStreamOperator;

public class RedJavaInputStreamOperator implements JavaInputStreamOperator {

	private InputStream java_input_stream;

	public RedJavaInputStreamOperator (final InputStream java_input_stream) {
		this.java_input_stream = java_input_stream;
	}

	@Override
	public void closeStream () {
		IO.forceClose(this.java_input_stream);
		this.java_input_stream = null;
	}

	@Override
	public InputStream getJavaStream () {
		return this.java_input_stream;
	}

}
