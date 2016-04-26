
package com.jfixby.red.io;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.JavaOutputStreamOperator;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;

public class RedBufferOutputStreamOperator implements JavaOutputStreamOperator {

	private ByteArrayOutputStream os;

	@Override
	public void closeStream () {
		IO.forceClose(this.os);
		this.os = null;
	}

	@Override
	public OutputStream getJavaStream () {
		if (this.os == null) {
			this.os = new ByteArrayOutputStream();
		}
		return this.os;
	}

	public ByteArray getBytes () {
		return JUtils.newByteArray(this.os.toByteArray());
	}

}
