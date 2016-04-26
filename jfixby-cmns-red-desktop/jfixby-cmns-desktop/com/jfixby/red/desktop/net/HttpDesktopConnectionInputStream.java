
package com.jfixby.red.desktop.net;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.STREAM_STATE;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;

public class HttpDesktopConnectionInputStream implements HttpConnectionInputStream {

	public HttpDesktopConnectionInputStream (final InputStream java_input_stream) {
		Err.reportError("Not implemented yet");
	}

	@Override
	public boolean hasData () throws IOException {
		return false;
	}

	@Override
	public Data read () throws IOException {
		return null;
	}

	@Override
	public int available () throws IOException {
		return 0;
	}

	@Override
	public ByteArray readAll () throws IOException {
		return null;
	}

	@Override
	public InputStream toJavaInputStream () {
		return null;
	}

	@Override
	public void close () {
	}

	@Override
	public STREAM_STATE getState () {
		return null;
	}

	@Override
	public void open () {
	}

}
