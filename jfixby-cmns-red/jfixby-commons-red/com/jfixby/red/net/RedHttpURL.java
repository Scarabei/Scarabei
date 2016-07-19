
package com.jfixby.red.net;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.net.http.Host;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;

public class RedHttpURL implements HttpURL {

	private final String url_string;

	public RedHttpURL (final String url_string) {
		Debug.checkEmpty("url_string", url_string);
		Debug.checkNull("url_string", url_string);
		this.url_string = url_string;
		Err.reportError("Not implemented yet! Needs url parsing.");
	}

	@Override
	public String toString () {
		return this.url_string;
	}

	@Override
	public String getURLString () {
		return this.url_string;
	}

	@Override
	public Host getMountPoint () {
		return null;
	}

	@Override
	public RelativePath getRelativePath () {
		return null;
	}

	@Override
	public AbsolutePath<Host> child (final String child_name) {
		return null;
	}

	@Override
	public AbsolutePath<Host> parent () {
		return null;
	}

	@Override
	public AbsolutePath<Host> proceed (final RelativePath relative) {
		return null;
	}

	@Override
	public String getName () {
		return null;
	}

	@Override
	public boolean isRoot () {
		return false;
	}

	@Override
	public boolean beginsWith (final AbsolutePath<? extends Host> rootPath) {
		return false;
	}

}
