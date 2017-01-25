
package com.jfixby.scarabei.api.net.http;

import java.io.IOException;

public interface HttpConnection {

	CONNECTION_STATE getState ();

	void open ();

	HttpConnectionInputStream getInputStream ();

	void close ();

	int getResponseCode () throws IOException;

	HttpConnectionOutputStream getOutputStream ();

	String getRequestUrlString ();

}
