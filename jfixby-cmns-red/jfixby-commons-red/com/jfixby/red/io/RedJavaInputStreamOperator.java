
package com.jfixby.red.io;

import java.io.InputStream;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.io.JavaInputStreamOperator;

public class RedJavaInputStreamOperator implements JavaInputStreamOperator {

	public RedJavaInputStreamOperator (final InputStream java_input_stream) {
		Err.reportError("Not implemented yet");
	}

	@Override
	public void closeStream () {
	}

	@Override
	public InputStream getJavaStream () {
		return null;
	}

}
