
package com.jfixby.red.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.JavaInputStreamOperator;
import com.jfixby.cmns.api.java.ByteArray;

public class RedGZipInputStreamOperator implements JavaInputStreamOperator {

	private final com.jfixby.cmns.api.io.InputStream input_stream;
	private GZIPInputStream gzip;

	public RedGZipInputStreamOperator (final com.jfixby.cmns.api.io.InputStream input_stream) {
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
		Err.reportNotImplementedYet();
		return null;
	}
}
