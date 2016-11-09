
package com.jfixby.red.desktop.net;

import com.jfixby.cmns.api.net.http.HttpCall;
import com.jfixby.cmns.api.net.http.HttpCallExecutor;
import com.jfixby.cmns.api.net.http.HttpCallParams;
import com.jfixby.cmns.api.net.http.HttpComponent;
import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionSpecs;
import com.jfixby.cmns.api.net.http.HttpFileSystem;
import com.jfixby.cmns.api.net.http.HttpFileSystemSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.red.filesystem.http.RedHttpFileSystem;
import com.jfixby.red.filesystem.http.RedHttpFileSystemSpecs;
import com.jfixby.red.net.RedCall;
import com.jfixby.red.net.RedCallExecutor;
import com.jfixby.red.net.RedHttpCallParams;
import com.jfixby.red.net.RedHttpURL;

public class HttpDesktopComponent implements HttpComponent {

	@Override
	public HttpURL newURL (final String url_string) {
		return new RedHttpURL(url_string);
	}

	@Override
	public HttpConnection newConnection (final HttpURL url) {
		return new DesktopHttpConnection(url, false);
	}

	@Override
	public HttpConnectionSpecs newConnectionSpecs () {
		return new DesktopHttpConnectionSpecs();
	}

	@Override
	public HttpConnection newConnection (final HttpConnectionSpecs specs) {
		final HttpURL url = specs.getURL();
		final boolean use_agent = specs.getUseAgent();
		return new DesktopHttpConnection(url, use_agent);
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
