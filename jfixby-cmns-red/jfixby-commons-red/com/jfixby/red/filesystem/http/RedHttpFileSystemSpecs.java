
package com.jfixby.red.filesystem.http;

import com.jfixby.cmns.api.net.http.HttpFileSystemSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;

public class RedHttpFileSystemSpecs implements HttpFileSystemSpecs {

	HttpURL url;

	@Override
	public void setRootUrl (final HttpURL url) {
		this.url = url;
	}

	public HttpURL getRootUrl () {
		return this.url;
	}

}
