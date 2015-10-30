package com.jfixby.red.net;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.net.http.HttpConnectionSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;

public class RedConnectionSpecs implements HttpConnectionSpecs {

	private HttpURL url;
	private boolean set_agent;

	@Override
	public void setURL(HttpURL url) {
		JUtils.checkNull("url", url);
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

}
