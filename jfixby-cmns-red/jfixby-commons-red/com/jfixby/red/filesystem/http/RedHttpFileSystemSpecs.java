
package com.jfixby.red.filesystem.http;

import com.jfixby.cmns.api.net.http.HttpFileSystemSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;

public class RedHttpFileSystemSpecs implements HttpFileSystemSpecs {

	HttpURL url;
	private int cache = 200;

	@Override
	public void setRootUrl (final HttpURL url) {
		this.url = url;
	}

	@Override
	public HttpURL getRootUrl () {
		return this.url;
	}

	@Override
	public void setCacheSize (final int cache) {
		this.cache = cache;
	}

	@Override
	public int getCacheSize () {
		return this.cache;
	}

}
