
package com.jfixby.scarabei.red.net.http;

import com.jfixby.scarabei.api.net.http.HttpFileSystemSpecs;
import com.jfixby.scarabei.api.net.http.HttpURL;

class RedHttpFileSystemSpecs implements HttpFileSystemSpecs {

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
