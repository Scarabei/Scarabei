package com.jfixby.red.net;

import com.jfixby.cmns.api.net.http.HttpCallSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.cmns.api.net.http.METHOD;

public class RedCallSpecs implements HttpCallSpecs {

	private HttpURL url;
	private boolean set_agent;
	private METHOD method;
	private boolean ssl;

	@Override
	public void setURL(HttpURL url) {
		this.url = url;

	}

	@Override
	public void setUseAgent(boolean b) {
		this.set_agent = b;
	}

	@Override
	public HttpURL getURL() {
		return url;
	}

	@Override
	public boolean getUseAgent() {
		return set_agent;
	}

	@Override
	public void setMethod(METHOD method) {
		this.method = method;
	}

	@Override
	public METHOD getMethod() {
		return this.method;
	}

	@Override
	public void setUseSSL(boolean use_ssl) {
		this.ssl = use_ssl;
	}

	@Override
	public boolean getUseSSL() {
		return ssl;
	}

}
