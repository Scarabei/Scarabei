
package com.jfixby.scarabei.red.net.http;

import java.net.URL;

import com.jfixby.scarabei.api.net.http.HttpURL;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.net.RedHost;

public class RedHttpURL implements HttpURL {

	// private final String url_string;
	final private RedHost host;
	private final String url_string;
	private final RelativePath relative;

	public RedHttpURL (final RedHost host, final RelativePath relative) {
		this.host = host;
		this.relative = relative;
		this.url_string = this.toURLString(this);
	}

	private String toURLString (final RedHttpURL redHttpURL) {
		final String result = this.host.rootString + RelativePath.SEPARATOR + this.relative;
		return result;
	}

	@Override
	public RelativePath getRelativePath () {
		return this.relative;
	}

	@Override
	public HttpURL child (final String child) {
		final RelativePath relativeNew = this.relative.child(child);
		return new RedHttpURL(this.host, relativeNew);
	}

	public static String getURLRoot (final URL java_url) {
		final int port = java_url.getPort();
		if (port == -1) {
			return java_url.getProtocol() + "://" + java_url.getHost();
		}
		return java_url.getProtocol() + "://" + java_url.getHost() + ":" + port;
	}

	@Override
	public String toString () {
		return this.url_string;
	}

	@Override
	public String getURLString () {
		return this.url_string;
	}

// @Override
// public Host getMountPoint () {
// return this.host;
// }
//
// @Override
// public RelativePath getRelativePath () {
// return this.abs.getRelativePath();
// }
//
// @Override
// public AbsolutePath<Host> child (final String child_name) {
// return this.abs.child(child_name);
// }
//
// @Override
// public AbsolutePath<Host> parent () {
// return this.abs.parent();
// }
//
// @Override
// public AbsolutePath<Host> proceed (final RelativePath relative) {
// return this.abs.proceed(relative);
// }
//
// @Override
// public String getName () {
// return this.abs.getName();
// }
//
// @Override
// public boolean isRoot () {
// return this.abs.isRoot();
// }
//
// @Override
// public boolean beginsWith (final AbsolutePath<? extends Host> rootPath) {
// return this.abs.beginsWith(rootPath);
// }

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
