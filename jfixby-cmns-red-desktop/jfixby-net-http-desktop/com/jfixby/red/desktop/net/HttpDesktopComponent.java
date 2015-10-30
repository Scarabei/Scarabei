package com.jfixby.red.desktop.net;

import com.jfixby.cmns.api.net.http.HttpCall;
import com.jfixby.cmns.api.net.http.HttpCallExecutor;
import com.jfixby.cmns.api.net.http.HttpCallSpecs;
import com.jfixby.cmns.api.net.http.HttpComponent;
import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.red.net.RedCall;
import com.jfixby.red.net.RedCallExecutor;
import com.jfixby.red.net.RedCallSpecs;

public class HttpDesktopComponent implements HttpComponent {

	@Override
	public HttpURL newURL(String url_string) {
		return new UrlImpl(url_string);
	}

	@Override
	public HttpConnection newConnection(HttpURL url) {
		return new DesktopHttpConnection(url, false);
	}

	@Override
	public HttpConnectionSpecs newConnectionSpecs() {
		return new DesktopHttpConnectionSpecs();
	}

	@Override
	public HttpConnection newConnection(HttpConnectionSpecs specs) {
		HttpURL url = specs.getURL();
		boolean use_agent = specs.getUseAgent();
		return new DesktopHttpConnection(url, use_agent);
	}

	@Override
	public HttpCallSpecs newCallSpecs() {
		return new RedCallSpecs();
	}

	@Override
	public HttpCallExecutor newCallExecutor() {
		return new RedCallExecutor();
	}

	@Override
	public HttpCall newCall(HttpCallSpecs call_scecs) {
		return new RedCall(call_scecs);
	}

}
