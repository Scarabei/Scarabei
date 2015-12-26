package com.jfixby.red.desktop.net;

import com.jfixby.cmns.api.net.http.HttpURL;

public class UrlImpl implements HttpURL {

	private String url_string;

	public UrlImpl(String url_string) {
		this.url_string = url_string;
	}

	@Override
	public String toString() {
		return "Url<" + url_string + ">";
	}

	@Override
	public String getURLString() {
		return url_string;
	}

}
