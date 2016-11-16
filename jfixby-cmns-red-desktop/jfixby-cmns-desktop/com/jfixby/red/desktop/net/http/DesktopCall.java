
package com.jfixby.red.desktop.net.http;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.net.http.HttpCall;
import com.jfixby.cmns.api.net.http.HttpCallParams;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.cmns.api.net.http.METHOD;

public class DesktopCall implements HttpCall {

	final Map<String, Object> map = Collections.newMap();
	private HttpURL url;
	private METHOD method;
	private boolean agent;
	private boolean ssl;

	public METHOD getMethod () {
		return method;
	}

	public void setMethod (METHOD method) {
		this.method = Debug.checkNull("method", method);
	}

	public DesktopCall (HttpCallParams call_scecs) {
		url = call_scecs.getURL();
		method = Debug.checkNull("method", call_scecs.getMethod());
		agent = call_scecs.getUseAgent();
		ssl = call_scecs.getUseSSL();
	}

	@Override
	public void addRequestHeader (String parameter_name, Object value) {
		map.put(parameter_name, value);
	}

	@Override
	public HttpURL getUrl () {
		return url;
	}

	@Override
	public boolean getUseSSL () {
		return ssl;
	}

	@Override
	public boolean getUseAgent () {
		return agent;
	}

}
