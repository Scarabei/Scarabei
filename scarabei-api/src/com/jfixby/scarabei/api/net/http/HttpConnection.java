
package com.jfixby.scarabei.api.net.http;

import java.io.IOException;
import java.net.MalformedURLException;

public interface HttpConnection {

	void open () throws MalformedURLException, IOException;

	HttpConnectionInputStream getInputStream ();

	void close ();

	int getResponseCode () throws IOException;

	HttpConnectionOutputStream getOutputStream ();

}
