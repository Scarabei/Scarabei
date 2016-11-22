
package com.jfixby.red.net;

import java.net.MalformedURLException;
import java.net.URL;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.net.http.Host;
import com.jfixby.cmns.api.net.http.HttpURL;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;

public class RedHttpURL implements HttpURL {

	// private final String url_string;
	private URL java_url;
	private RelativePath relativeServer;
	private AbsolutePath<Host> abs;
	private String rootString;
	private RedHost host;
	private String url_string;

	public RedHttpURL (final String url_string) {
		Debug.checkEmpty("url_string", url_string);
		Debug.checkNull("url_string", url_string);
// this.url_string = url_string;
		try {

			this.java_url = new URL(url_string);
			this.url_string = this.java_url.toString();
			this.rootString = getURLRoot(this.java_url);
			final String path = this.java_url.getPath();
			this.relativeServer = JUtils.newRelativePath(path);
			this.host = new RedHost(this.rootString);
			this.abs = JUtils.newAbsolutePath((Host)this.host, this.relativeServer);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			Err.reportError(e);
		}

	}

	public static String getURLRoot (final URL java_url) {
		return java_url.getProtocol() + "://" + java_url.getHost() + ":" + java_url.getPort();
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
		return this.host;
	}

	@Override
	public RelativePath getRelativePath () {
		return this.abs.getRelativePath();
	}

	@Override
	public AbsolutePath<Host> child (final String child_name) {
		return this.abs.child(child_name);
	}

	@Override
	public AbsolutePath<Host> parent () {
		return this.abs.parent();
	}

	@Override
	public AbsolutePath<Host> proceed (final RelativePath relative) {
		return this.abs.proceed(relative);
	}

	@Override
	public String getName () {
		return this.abs.getName();
	}

	@Override
	public boolean isRoot () {
		return this.abs.isRoot();
	}

	@Override
	public boolean beginsWith (final AbsolutePath<? extends Host> rootPath) {
		return this.abs.beginsWith(rootPath);
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.url_string == null) ? 0 : this.url_string.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedHttpURL other = (RedHttpURL)obj;
		if (this.url_string == null) {
			if (other.url_string != null) {
				return false;
			}
		} else if (!this.url_string.equals(other.url_string)) {
			return false;
		}
		return true;
	}

}
