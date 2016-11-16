
package com.jfixby.red.android.net.http;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.cmns.api.net.http.HttpURL;

public class AndroidHttpConnection implements HttpConnection {

	private final HttpURL url;
	private final boolean use_agent;

	private URLConnection java_connection;
	private URL java_url;
	private AndroidHttpConnectionInputStream red_input_stream;

	public AndroidHttpConnection (final HttpURL url, final boolean use_agent) {
		this.url = url;
		this.use_agent = use_agent;
	}

	@Override
	public void open () throws IOException {
		this.java_url = new java.net.URL(this.url.getURLString());

		this.java_connection = this.java_url.openConnection();
		if (this.use_agent) {
			this.java_connection.addRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
			this.java_connection.addRequestProperty("Accept-Language", "ru-RU,ru;q=0.8");

		}
	}

	@Override
	public HttpConnectionInputStream getInputStream () {
		if (this.red_input_stream == null) {
			this.red_input_stream = new AndroidHttpConnectionInputStream(this.java_connection);
		}
		return this.red_input_stream;
	}

	@Override
	public void close () {
		if (this.red_input_stream == null) {
			return;
		}
		if (this.red_input_stream.isOpen()) {
			this.red_input_stream.close();
		}
	}

}
