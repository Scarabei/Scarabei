
package com.jfixby.scarabei.examples.http;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.net.http.HttpConnection;
import com.jfixby.scarabei.api.net.http.HttpConnectionInputStream;
import com.jfixby.scarabei.api.net.http.HttpURL;
import com.jfixby.scarabei.api.util.JUtils;

public class HttpExample {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();

		{
			final HttpURL url = Http.newURL("http://google.com");
			final String stringData = HttpExample.readDataToString(url);
			L.d("stringData", stringData);
		}
		{
			final HttpURL url = Http.newURL("http://badURL");
			final String stringData = HttpExample.readDataToString(url);
			L.d("stringData", stringData);
		}
	}

	private static String readDataToString (final HttpURL url) {
		String stringData = null;

		final HttpConnection connect = Http.newConnection(url);
		connect.open();
		final HttpConnectionInputStream is = connect.getInputStream();
		is.open();
		try {
			final ByteArray data = is.readAll();
			stringData = JUtils.newString(data, "UTF-8");
		} catch (final Exception e) {
			L.e(e);
		}

		is.close();
		connect.close();

		return stringData;
	}

}
