
package com.jfixby.scarabei.red.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.scarabei.api.io.Buffer;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.JavaInputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;

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

	@Override
	public boolean isReadAllSupported () {
		return true;
	}

	@Override
	public ByteArray readAll () throws IOException {
		return this.buffer.getBytes();
	}

}
