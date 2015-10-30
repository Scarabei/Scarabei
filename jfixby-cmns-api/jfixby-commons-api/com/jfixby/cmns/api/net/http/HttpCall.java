package com.jfixby.cmns.api.net.http;

public interface HttpCall {

	void addRequestHeader(String parameter_name, Object value);

	HttpURL getUrl();

	boolean getUseSSL();
	
	boolean getUseAgent();

}
