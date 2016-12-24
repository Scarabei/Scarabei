
package com.jfixby.scarabei.red.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.JavaOutputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;

public class RedGZipOutputStreamOperator implements JavaOutputStreamOperator {

	private final com.jfixby.scarabei.api.io.OutputStream bos;
	private GZIPOutputStream os;

	public RedGZipOutputStreamOperator (final com.jfixby.scarabei.api.io.OutputStream os) {
		this.bos = os;

	}

	@Override
	public void closeStream () {
		if (this.os == null) {
			return;
		}
		IO.forceClose(this.os);
		this.os = null;
	}

	@Override
	public OutputStream getJavaStream () throws IOException {
		if (this.os == null) {
			final OutputStream jos = this.bos.toJavaOutputStream();
			this.os = new GZIPOutputStream(jos);

		}
		return this.os;
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
