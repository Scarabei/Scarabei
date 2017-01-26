
package com.jfixby.scarabei.api.net.http;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Mapping;

public interface HttpConnection {

	CONNECTION_STATE getState ();

	void open ();

	HttpConnectionInputStream getInputStream ();

	void close ();

	int getResponseCode () throws IOException;

	HttpConnectionOutputStream getOutputStream ();

	String getRequestUrlString ();

	public Mapping<String, Collection<String>> getResponseHeaders () throws IOException;

	String getRedirectUrlString () throws IOException;

}
