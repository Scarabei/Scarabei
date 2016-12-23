
package com.jfixby.red.io;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.InputStreamOpener;
import com.jfixby.cmns.api.io.JavaInputStreamOperator;
import com.jfixby.cmns.api.java.ByteArray;

public class RedJavaInputStreamOperator implements JavaInputStreamOperator {

	private InputStream java_input_stream;
	private final InputStreamOpener opener;

	public RedJavaInputStreamOperator (final InputStreamOpener opener) {
		this.opener = opener;
	}

	@Override
	public void closeStream () {
		IO.forceClose(this.java_input_stream);
		this.java_input_stream = null;
	}

	@Override
	public InputStream getJavaStream () throws IOException {
		if (this.java_input_stream == null) {
			this.java_input_stream = this.opener.open();
		}
		return this.java_input_stream;
	}

	@Override
	public boolean isReadAllSupported () {
		return false;
	}

	@Override
	public ByteArray readAll () throws IOException {
		return null;
	}

}
