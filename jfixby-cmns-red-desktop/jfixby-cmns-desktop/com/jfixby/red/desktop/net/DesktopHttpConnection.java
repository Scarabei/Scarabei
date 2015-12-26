package com.jfixby.red.desktop.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.cmns.api.net.http.HttpURL;

public class DesktopHttpConnection implements HttpConnection {

	private HttpURL url;
	private boolean use_agent;
	private InputStream java_input_stream;
	private URLConnection java_connection;
	private URL java_url;

	public DesktopHttpConnection(HttpURL url, boolean use_agent) {
		this.url = url;
		this.use_agent = use_agent;
	}

	@Override
	public void open() throws IOException {
		java_url = new java.net.URL(url.getURLString());

		java_connection = java_url.openConnection();
		if (use_agent) {
			java_connection
					.addRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
			java_connection.addRequestProperty("Accept-Language",
					"ru-RU,ru;q=0.8");

		}
	}

	@Override
	public HttpConnectionInputStream getInputStream() throws IOException {
		java_input_stream = java_connection.getInputStream();

		HttpDesktopConnectionInputStream red_input_stream = new HttpDesktopConnectionInputStream(
				java_input_stream);

		return red_input_stream;
	}

	@Override
	public void close() {
		try {
			java_input_stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
