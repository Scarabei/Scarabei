
package com.jfixby.scarabei.red.net.http;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.net.http.HttpCall;
import com.jfixby.scarabei.api.net.http.HttpCallParams;
import com.jfixby.scarabei.api.net.http.HttpURL;
import com.jfixby.scarabei.api.net.http.METHOD;

public class RedCall implements HttpCall {

	final Map<String, String> map = Collections.newMap();
	private final HttpURL url;
	private METHOD method;
	private final boolean agent;
	private final boolean ssl;

	public METHOD getMethod () {
		return this.method;
	}

	public void setMethod (final METHOD method) {
		this.method = Debug.checkNull("method", method);
	}

	public RedCall (final HttpCallParams call_scecs) {
		this.url = call_scecs.getURL();
		this.method = Debug.checkNull("method", call_scecs.getMethod());
		this.agent = call_scecs.getUseAgent();
		this.ssl = call_scecs.getUseSSL();
	}

	@Override
	public void addRequestHeader (final String parameter_name, final String value) {
		this.map.put(parameter_name, value);
	}

	@Override
	public HttpURL getUrl () {
		return this.url;
	}

	@Override
	public boolean getUseSSL () {
		return this.ssl;
	}

	@Override
	public boolean getUseAgent () {
		return this.agent;
	}

	@Override
	public void addRequestHeaders (final Mapping<String, String> headers) {
		this.map.putAll(headers);
	}

	@Override
	public Mapping<String, String> listRequestHeaders () {
		return this.map;
	}

}
