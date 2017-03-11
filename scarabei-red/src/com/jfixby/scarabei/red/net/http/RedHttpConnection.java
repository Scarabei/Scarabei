
package com.jfixby.scarabei.red.net.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.net.http.CONNECTION_STATE;
import com.jfixby.scarabei.api.net.http.HttpConnection;
import com.jfixby.scarabei.api.net.http.HttpConnectionInputStream;
import com.jfixby.scarabei.api.net.http.HttpConnectionOutputStream;
import com.jfixby.scarabei.api.net.http.HttpConnectionSpecs;
import com.jfixby.scarabei.api.net.http.HttpURL;
import com.jfixby.scarabei.api.net.http.METHOD;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.StateSwitcher;

public class RedHttpConnection implements HttpConnection {

	private final HttpURL url;
	private final boolean use_agent;
	final Map<String, String> requestProperties = Collections.newMap();

	HttpURLConnection java_connection;
	private URL java_url;
	private RedHttpConnectionInputStream red_input_stream;
	private RedHttpConnectionOutputStream red_output_stream;
// private final int code = -1;
	private boolean doInput = true;
	private boolean doOutput = true;
	METHOD method = METHOD.GET;
	private boolean useCaches;
	private boolean defaultUseCaches;
	private boolean octetStream;
	private long connectionTimeout;
	private long readTimeout;
	private final StateSwitcher<CONNECTION_STATE> state = JUtils.newStateSwitcher(CONNECTION_STATE.NEW);
	private boolean instanceFollowRedirects;

	public RedHttpConnection (final HttpConnectionSpecs specs) {
		this.url = specs.getURL();
		this.use_agent = specs.getUseAgent();
		this.requestProperties.putAll(specs.listRequestProperties());
		this.doInput = specs.doInput();
		this.doOutput = specs.doOutput();
		this.method = specs.getMethod();
		this.useCaches = specs.useCaches();
		this.defaultUseCaches = specs.defaultUseCaches();
		this.octetStream = specs.octetStream();
		this.connectionTimeout = specs.getConnectionTimeout();
		this.readTimeout = specs.getReadTimeout();
		this.instanceFollowRedirects = specs.getInstanceFollowRedirects();
	}

	public RedHttpConnection (final HttpURL url, final boolean use_agent) {
		this.url = url;
		this.use_agent = use_agent;
	}

	@Override
	public void open () {
		this.state.expectState(CONNECTION_STATE.NEW);
		this.state.switchState(CONNECTION_STATE.OPEN);
	}

	@Override
	public String getRedirectUrlString () throws IOException {
		this.state.expectState(CONNECTION_STATE.OPEN);
		if (this.java_connection == null) {
			this.tryToOpenConnection();
		}
		final URL urlResult = this.java_connection.getURL();
		return urlResult.toString();
	}

	@Override
	public Mapping<String, Collection<String>> getResponseHeaders () throws IOException {
		this.state.expectState(CONNECTION_STATE.OPEN);
		if (this.java_connection == null) {
			this.tryToOpenConnection();
		}
		final Map<String, Collection<String>> fields = this.wrapMap(this.java_connection.getHeaderFields());
// fields.print("HeaderFields");
// return this.java_connection.getHeaderField(key);
		return fields;
	}

	private Map<String, Collection<String>> wrapMap (final java.util.Map<String, java.util.List<String>> headerFields) {
		final Map<String, Collection<String>> result = Collections.newMap();
		final java.util.Set<String> keys = headerFields.keySet();
		for (final String key : keys) {
			final List<String> value = Collections.newList(headerFields.get(key));
			result.put(key, value);
		}
		return result;
	}

	void tryToOpenConnection () throws IOException {

		this.java_url = new java.net.URL(this.getRequestUrlString());

		this.java_connection = (HttpURLConnection)this.java_url.openConnection();

		this.java_connection.setRequestMethod(this.method.toJavaString());
		this.java_connection.setUseCaches(this.useCaches);
		this.java_connection.setDefaultUseCaches(this.defaultUseCaches);
		this.java_connection.setDoInput(this.doInput);
		this.java_connection.setDoOutput(this.doOutput);
		this.java_connection.setInstanceFollowRedirects(this.instanceFollowRedirects);

// this.code = this.java_connection.getResponseCode();
		if (this.use_agent) {
			this.java_connection.addRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
			this.java_connection.addRequestProperty("Accept-Language", "ru-RU,ru;q=0.8");

		}

		if (this.octetStream) {
			this.java_connection.setRequestProperty("Content-Type", "application/octet-stream");
		}
		if (this.method == METHOD.POST) {
			for (final String key : this.requestProperties.keys()) {
				this.java_connection.addRequestProperty(key, this.requestProperties.get(key));
			}
		}

		this.java_connection.setConnectTimeout((int)this.connectionTimeout);
		this.java_connection.setReadTimeout((int)this.readTimeout);

		this.java_connection.connect();

// final String rm = this.java_connection.getResponseMessage();
// L.d("ResponseMessage", rm);
	}

	@Override
	public HttpConnectionInputStream getInputStream () {
		this.state.expectState(CONNECTION_STATE.OPEN);
		if (this.red_input_stream == null) {
			this.red_input_stream = new RedHttpConnectionInputStream(this);
		}
		return this.red_input_stream;
	}

	@Override
	public HttpConnectionOutputStream getOutputStream () {
		this.state.expectState(CONNECTION_STATE.OPEN);
// Debug.checkTrue("Connection is not open " + this.url, this.java_connection != null);
		if (this.red_output_stream == null) {
			this.red_output_stream = new RedHttpConnectionOutputStream(this);
		}
		return this.red_output_stream;
	}

	@Override
	public void close () {
		this.state.expectState(CONNECTION_STATE.OPEN);
		this.state.switchState(CONNECTION_STATE.CLOSED);
		IO.forceClose(this.red_output_stream);
		IO.forceClose(this.red_input_stream);
	}

	@Override
	public int getResponseCode () throws IOException {
		this.state.expectState(CONNECTION_STATE.OPEN);
		if (this.java_connection == null) {
			this.tryToOpenConnection();
		}
		return this.java_connection.getResponseCode();
	}

	@Override
	public CONNECTION_STATE getState () {
		return this.state.currentState();
	}

	@Override
	public String getRequestUrlString () {

		String urlString = this.url.getURLString();

		if (this.method == METHOD.GET) {
			final List<String> list = Collections.newList();
			for (final String key : this.requestProperties.keys()) {
				list.add(key + "=" + this.requestProperties.get(key));
			}
			urlString = urlString + JUtils.wrapSequence(list, list.size(), "?", "", "&");
		}

		return urlString;
	}

}
