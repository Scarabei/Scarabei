package com.jfixby.red.desktop.net;

import com.jfixby.cmns.api.net.http.HttpConnectionSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;

public class DesktopHttpConnectionSpecs implements HttpConnectionSpecs {

	private HttpURL url;
	private boolean agent;

	@Override
	public void setURL(HttpURL url) {
		this.url = url;
	}

	@Override
	public void setUseAgent(boolean agent) {
		this.agent = agent;
	}

	@Override
	public HttpURL getURL() {
		return url;
	}

	@Override
	public boolean getUseAgent() {
		return agent;
	}

}
