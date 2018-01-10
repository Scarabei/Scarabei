
package com.jfixby.scarabei.red.net.http;

import javax.net.ssl.SSLSocketFactory;

import com.jfixby.scarabei.api.net.http.HttpCallParams;
import com.jfixby.scarabei.api.net.http.HttpURL;
import com.jfixby.scarabei.api.net.http.METHOD;

public class RedHttpCallParams implements HttpCallParams {

	private HttpURL url;
	private boolean set_agent;
	private METHOD method = METHOD.GET;
	private boolean ssl;
	private SSLSocketFactory sslFactory;

	@Override
	public void setURL (final HttpURL url) {
		this.url = url;

	}

	@Override
	public void setUseAgent (final boolean b) {
		this.set_agent = b;
	}

	@Override
	public HttpURL getURL () {
		return this.url;
	}

	@Override
	public boolean getUseAgent () {
		return this.set_agent;
	}

	@Override
	public void setMethod (final METHOD method) {
		this.method = method;
	}

	@Override
	public METHOD getMethod () {
		return this.method;
	}

	@Override
	public void setUseSSL (final boolean use_ssl) {
		this.ssl = use_ssl;
	}

	@Override
	public boolean getUseSSL () {
		return this.ssl;
	}

	@Override
	public void setSSLFactory (final SSLSocketFactory sslFactory) {
		this.sslFactory = sslFactory;
	}

	@Override
	public SSLSocketFactory getSSLFactory () {
		return this.sslFactory;
	}

}
