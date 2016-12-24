
package com.jfixby.scarabei.red.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.JavaOutputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.JUtils;

public class RedBufferOutputStreamOperator implements JavaOutputStreamOperator {

	private ByteArrayOutputStream os;
	byte[] data = new byte[0];

	@Override
	public void closeStream () {
		IO.forceClose(this.os);
		this.data = this.os.toByteArray();
		this.os = null;
	}

	public ByteArray getBytes () {
		return JUtils.newByteArray(this.data);
	}

	@Override
	public OutputStream getJavaStream () {
		if (this.os == null) {
			this.os = new ByteArrayOutputStream(1024 * 4);
		}
		return this.os;
	}

	@Override
	public boolean isBulkWriteSupported () {
		return true;
	}

	@Override
	public void writeAll (final ByteArray bytes) throws IOException {
		this.data = bytes.toArray();
	}

}
