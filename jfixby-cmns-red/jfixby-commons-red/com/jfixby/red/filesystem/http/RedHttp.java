
package com.jfixby.red.filesystem.http;

import com.jfixby.cmns.api.net.http.HttpCall;
import com.jfixby.cmns.api.net.http.HttpCallExecutor;
import com.jfixby.cmns.api.net.http.HttpCallParams;
import com.jfixby.cmns.api.net.http.HttpComponent;
import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionSpecs;
import com.jfixby.cmns.api.net.http.HttpFileSystem;
import com.jfixby.cmns.api.net.http.HttpFileSystemSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.red.filesystem.http.fs.RedHttpFileSystem;
import com.jfixby.red.filesystem.http.fs.RedHttpFileSystemSpecs;
import com.jfixby.red.net.RedHttpURL;

public abstract class RedHttp implements HttpComponent {

	@Override
	public HttpURL newURL (final String url_string) {
		return new RedHttpURL(url_string);
	}

	@Override
	public HttpConnection newConnection (final HttpURL url) {
		return new RedHttpConnection(url, false);
	}

	@Override
	public HttpConnectionSpecs newConnectionSpecs () {
		return new RedHttpConnectionSpecs();
	}

	@Override
	public HttpConnection newConnection (final HttpConnectionSpecs specs) {
		return new RedHttpConnection(specs);
	}

	@Override
	public HttpCallParams newCallParams () {
		return new RedHttpCallParams();
	}

	@Override
	public HttpCallExecutor newCallExecutor () {
		return new RedCallExecutor();
	}

	@Override
	public HttpCall newCall (final HttpCallParams call_scecs) {
		return new RedCall(call_scecs);
	}

	@Override
	public HttpFileSystemSpecs newHttpFileSystemSpecs () {
		return new RedHttpFileSystemSpecs();
	}

	@Override
	public HttpFileSystem newHttpFileSystem (final HttpFileSystemSpecs specs) {
		return new RedHttpFileSystem(specs);
	}

}
