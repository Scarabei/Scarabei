
package com.jfixby.red.android.net.http;

import java.io.IOException;

import com.jfixby.cmns.api.net.http.Http;
import com.jfixby.cmns.api.net.http.HttpCall;
import com.jfixby.cmns.api.net.http.HttpCallExecutor;
import com.jfixby.cmns.api.net.http.HttpCallProgress;
import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.cmns.api.net.http.HttpConnectionSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;

public class AndroidCallExecutor implements HttpCallExecutor {

	@Override
	public HttpCallProgress execute (final HttpCall call) throws IOException {
		final boolean use_ssl = call.getUseSSL();
		if (use_ssl) {
			throw new Error("Not implemented yet");
		}

		final HttpURL url = call.getUrl();

		final HttpConnectionSpecs specs = Http.newConnectionSpecs();
		specs.setURL(url);
		specs.setUseAgent(true);
		final HttpConnection connection = Http.newConnection(specs);

		connection.open();

		final HttpConnectionInputStream input_stream = connection.getInputStream();
		input_stream.open();
		byte[] data;

		data = input_stream.readAll().toArray();

		connection.close();

		final AndroidHttpCallProgress progress = new AndroidHttpCallProgress(connection, data);
		return progress;
	}

}
