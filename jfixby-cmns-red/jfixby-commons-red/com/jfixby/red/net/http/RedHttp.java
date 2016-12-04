
package com.jfixby.red.net.http;

import java.net.MalformedURLException;
import java.net.URL;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.net.http.HttpCall;
import com.jfixby.cmns.api.net.http.HttpCallExecutor;
import com.jfixby.cmns.api.net.http.HttpCallParams;
import com.jfixby.cmns.api.net.http.HttpComponent;
import com.jfixby.cmns.api.net.http.HttpConnection;
import com.jfixby.cmns.api.net.http.HttpConnectionSpecs;
import com.jfixby.cmns.api.net.http.HttpFileSystem;
import com.jfixby.cmns.api.net.http.HttpFileSystemSpecs;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.http.fs.RedHttpFileSystem;
import com.jfixby.red.filesystem.http.fs.RedHttpFileSystemSpecs;
import com.jfixby.red.net.RedHost;

public abstract class RedHttp implements HttpComponent {

	@Override
	public HttpURL newURL (String url_string) {
		Debug.checkEmpty("url_string", url_string);
		Debug.checkNull("url_string", url_string);
		try {
			final URL java_url = new URL(url_string);
			url_string = java_url.toString();
			final RelativePath relative = JUtils.newRelativePath(java_url.getPath());
			final RedHost host = new RedHost(RedHttpURL.getURLRoot(java_url));
			return new RedHttpURL(host, relative);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			Err.reportError(e);
		}
		return null;
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
