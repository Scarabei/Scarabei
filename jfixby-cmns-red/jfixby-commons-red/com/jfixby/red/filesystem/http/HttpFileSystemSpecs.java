
package com.jfixby.red.filesystem.http;

import com.jfixby.cmns.api.net.http.HttpURL;

public class HttpFileSystemSpecs {

	HttpURL url;

	public void setRootUrl (final HttpURL url) {
		this.url = url;
	}

	public HttpURL getRootUrl () {
		return this.url;
	}

}
