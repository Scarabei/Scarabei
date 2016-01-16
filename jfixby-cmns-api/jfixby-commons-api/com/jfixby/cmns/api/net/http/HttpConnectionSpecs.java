package com.jfixby.cmns.api.net.http;

public interface HttpConnectionSpecs {

	void setURL(HttpURL url);

	void setUseAgent(boolean b);

	HttpURL getURL();

	boolean getUseAgent();

}
