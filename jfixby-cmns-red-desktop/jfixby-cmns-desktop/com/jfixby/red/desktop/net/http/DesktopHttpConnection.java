
package com.jfixby.red.desktop.net.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.cmns.api.net.http.HttpConnectionOutputStream;
import com.jfixby.cmns.api.net.http.HttpURL;

public class DesktopHttpConnection implements HttpConnection {

	private final HttpURL url;
	private final boolean use_agent;

	private HttpURLConnection java_connection;
	private URL java_url;
	private DesktopHttpConnectionInputStream red_input_stream;
	private DesktopHttpConnectionOutputStream red_output_stream;
	private int code = -1;

	public DesktopHttpConnection (final HttpURL url, final boolean use_agent) {
		this.url = url;
		this.use_agent = use_agent;
	}

	@Override
	public void open () throws IOException {
		this.java_url = new java.net.URL(this.url.getURLString());

		this.java_connection = (HttpURLConnection)this.java_url.openConnection();
		this.code = this.java_connection.getResponseCode();
		if (this.use_agent) {
			this.java_connection.addRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
			this.java_connection.addRequestProperty("Accept-Language", "ru-RU,ru;q=0.8");

		}
	}

	@Override
	public HttpConnectionInputStream getInputStream () {
		if (this.red_input_stream == null) {
			this.red_input_stream = new DesktopHttpConnectionInputStream(this.java_connection);
		}
		return this.red_input_stream;
	}

	@Override
	public HttpConnectionOutputStream getOutputStream () {
		if (this.red_output_stream == null) {
			this.red_output_stream = new DesktopHttpConnectionOutputStream(this.java_connection);
		}
		return this.red_output_stream;
	}

	@Override
	public void close () {
		IO.forceClose(this.red_output_stream);
		IO.forceClose(this.red_input_stream);
	}

	@Override
	public int getResponseCode () {
		return this.code;
	}

}
