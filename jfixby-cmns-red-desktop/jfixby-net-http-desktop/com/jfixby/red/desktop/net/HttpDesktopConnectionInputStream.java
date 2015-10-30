package com.jfixby.red.desktop.net;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class HttpDesktopConnectionInputStream extends AbstractRedInputStream implements HttpConnectionInputStream {

	public HttpDesktopConnectionInputStream(InputStream input_stream)
			throws IOException {
		super(input_stream);
	}

}