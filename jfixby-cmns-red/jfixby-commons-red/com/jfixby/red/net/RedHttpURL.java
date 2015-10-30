package com.jfixby.red.net;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.net.http.HttpURL;

public class RedHttpURL implements HttpURL {

	private String url_string;

	public RedHttpURL(String url_string) {
		JUtils.checkEmpty("url_string", url_string);
		JUtils.checkNull("url_string", url_string);
		this.url_string = url_string;
	}

	@Override
	public String toString() {
		return url_string;
	}

	@Override
	public String getURLString() {
		return url_string;
	}

}
