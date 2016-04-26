
package com.jfixby.red.io;

import java.io.OutputStream;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.io.JavaOutputStreamOperator;

public class RedJavaOutputStreamOperator implements JavaOutputStreamOperator {

	public RedJavaOutputStreamOperator (final OutputStream java_output_stream) {
		Err.reportError("Not implemented yet");
	}

	@Override
	public void closeStream () {
	}

	@Override
	public OutputStream getJavaStream () {
		return null;
	}

}
