
package com.jfixby.scarabei.red.net.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.scarabei.api.io.JavaInputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.net.http.HttpURL;

public class RedHttpFileInputStreamOperator implements JavaInputStreamOperator {

	private final RedHttpFile v_file;

	public RedHttpFileInputStreamOperator (final RedHttpFile input_file) {
		this.v_file = input_file;
	}

	@Override
	public void closeStream () {
	}

	@Override
	public InputStream getJavaStream () throws IOException {
		final RedHttpFileSystem fs = this.v_file.getFileSystem();
		final HttpURL url = fs.getURLFor(this.v_file.getAbsoluteFilePath());
		final ByteArray data = HTTPOperator.readFile(url);
		return new ByteArrayInputStream(data.toArray());
	}

	@Override
	public boolean isReadAllSupported () {
		return true;
	}

	@Override
	public ByteArray readAll () throws IOException {
		final RedHttpFileSystem fs = this.v_file.getFileSystem();
		final HttpURL url = fs.getURLFor(this.v_file.getAbsoluteFilePath());
		final ByteArray data = HTTPOperator.readFile(url);
		return data;
	}

}
