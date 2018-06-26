
package com.jfixby.scarabei.red.net.http;

import java.io.IOException;

import javax.net.ssl.SSLSocketFactory;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.net.http.HttpCall;
import com.jfixby.scarabei.api.net.http.HttpCallExecutor;
import com.jfixby.scarabei.api.net.http.HttpCallProgress;
import com.jfixby.scarabei.api.net.http.HttpConnection;
import com.jfixby.scarabei.api.net.http.HttpConnectionInputStream;
import com.jfixby.scarabei.api.net.http.HttpConnectionSpecs;
import com.jfixby.scarabei.api.net.http.HttpURL;

public class RedCallExecutor implements HttpCallExecutor {

	@Override
	public HttpCallProgress execute (final HttpCall call) throws IOException {
		final boolean use_ssl = call.getUseSSL();
		if (use_ssl) {
			Err.reportError("Not implemented yet");
		}

		final HttpURL url = call.getUrl();
		Debug.checkNull("url", url);
		final SSLSocketFactory sslFac = call.getSSLFactory();

		final HttpConnectionSpecs specs = Http.newConnectionSpecs();
		specs.setURL(url);
		specs.setSSLFactory(sslFac);
		specs.setUseAgent(call.getUseAgent());
		specs.addRequesrProperties(call.listRequestHeaders());
		specs.setDoOutput(true);
		specs.setDoInput(true);
		final long timeout = call.getServerTimeout();
		specs.setReadTimeout(timeout);
		specs.setConnectTimeout(timeout);
		specs.setInstanceFollowRedirects(true);
		specs.setOctetStream(false);
		final HttpConnection connection = Http.newConnection(specs);
// final connection.se
		connection.open();

		final HttpConnectionInputStream input_stream = connection.getInputStream();
		input_stream.open();
		byte[] data;

		data = input_stream.readAll().toArray();

		connection.close();

		final RedHttpCallProgress progress = new RedHttpCallProgress(connection, data);
		return progress;
	}

}
