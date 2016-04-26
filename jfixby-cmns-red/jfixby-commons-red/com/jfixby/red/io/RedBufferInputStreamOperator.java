
package com.jfixby.red.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.JavaInputStreamOperator;

public class RedBufferInputStreamOperator implements JavaInputStreamOperator {

	private final Buffer buffer;
	private ByteArrayInputStream bis;

	public RedBufferInputStreamOperator (final Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void closeStream () {
		IO.forceClose(this.bis);
		this.bis = null;
	}

	@Override
	public InputStream getJavaStream () {
		if (this.bis == null) {
			this.bis = new ByteArrayInputStream(this.buffer.getBytes().toArray());
		}
		return this.bis;
	}

}
