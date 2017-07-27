
package com.jfixby.scarabei.red.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.JavaInputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;

public class RedGZipInputStreamOperator implements JavaInputStreamOperator {

	private final com.jfixby.scarabei.api.io.InputStream input_stream;
	private GZIPInputStream gzip;

	public RedGZipInputStreamOperator (final com.jfixby.scarabei.api.io.InputStream input_stream) {
		this.input_stream = input_stream;

	}

	@Override
	public InputStream getJavaStream () throws IOException {
		if (this.gzip == null) {
			final InputStream is = this.input_stream.toJavaInputStream();
			this.gzip = new GZIPInputStream(is);
		}
		return this.gzip;
	}

	@Override
	public void closeStream () {
		if (this.gzip != null) {
			IO.forceClose(this.gzip);
		}
		this.gzip = null;
	}

	@Override
	public boolean isReadAllSupported () {
		return false;
	}

	@Override
	public ByteArray readAll () throws IOException {
		Err.throwNotImplementedYet();
		return null;
	}
}
