
package com.jfixby.scarabei.red.io;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.JavaOutputStreamOperator;
import com.jfixby.scarabei.api.io.OutputStreamOpener;
import com.jfixby.scarabei.api.java.ByteArray;

public class RedJavaOutputStreamOperator implements JavaOutputStreamOperator {

	private OutputStream java_Output_stream;
	private final OutputStreamOpener opener;

	public RedJavaOutputStreamOperator (final OutputStreamOpener opener) {
		this.opener = opener;
	}

	@Override
	public void closeStream () {
		IO.forceClose(this.java_Output_stream);
		this.java_Output_stream = null;
	}

	@Override
	public OutputStream getJavaStream () throws IOException {
		if (this.java_Output_stream == null) {
			this.java_Output_stream = this.opener.open();
		}
		return this.java_Output_stream;
	}

	@Override
	public boolean isBulkWriteSupported () {
		return false;
	}

	@Override
	public void writeAll (final ByteArray bytes) throws IOException {
		this.java_Output_stream.write(bytes.toArray());
	}

}
