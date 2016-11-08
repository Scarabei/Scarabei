
package com.jfixby.red.filesystem.http;

import com.jfixby.cmns.api.net.http.HttpURL;

public class HTTPFileInfo {

	private final HttpURL url;
	private int code;
	private long len;

	public HTTPFileInfo (final HttpURL url) {
		this.url = url;
	}

	public void setCode (final int code) {
		this.code = code;
	}

	public void setContentLength (final long len) {
		this.len = len;
	}

	@Override
	public String toString () {
		return "HTTPFileInfo [url=" + this.url + ", code=" + this.code + ", len=" + this.len + "]";
	}

	public boolean codeIs (final int value) {
		return this.code == value;
	}

	public long getContentLength () {
		return this.len;
	}

}
