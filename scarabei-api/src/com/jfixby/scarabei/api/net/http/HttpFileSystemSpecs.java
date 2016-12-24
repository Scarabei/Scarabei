
package com.jfixby.scarabei.api.net.http;

public interface HttpFileSystemSpecs {

	void setRootUrl (HttpURL url);

	HttpURL getRootUrl ();

	int getCacheSize ();

	void setCacheSize (int cache);

}
