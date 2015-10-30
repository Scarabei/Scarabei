package com.jfixby.red.net;

import java.io.IOException;

import com.jfixby.cmns.api.net.http.Http;
import com.jfixby.cmns.api.net.http.HttpCall;
import com.jfixby.cmns.api.net.http.HttpCallExecutor;
import com.jfixby.cmns.api.net.http.HttpCallProgress;
import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionInputStream;
import com.jfixby.cmns.api.net.http.HttpConnectionSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;

public class RedCallExecutor implements HttpCallExecutor {

	@Override
	public HttpCallProgress execute(HttpCall call) throws IOException {
		boolean use_ssl = call.getUseSSL();
		if (use_ssl) {
			throw new Error("Not implemented yet");
		}

		HttpURL url = call.getUrl();

		HttpConnectionSpecs specs = Http.newConnectionSpecs();
		specs.setURL(url);
		specs.setUseAgent(true);
		HttpConnection connection = Http.newConnection(specs);

		connection.open();

		HttpConnectionInputStream input_stream = connection.getInputStream();

		byte[] data;

		data = input_stream.readAll();

		connection.close();

		RedHttpCallProgress progress = new RedHttpCallProgress(connection, data);
		return progress;
	}

}
