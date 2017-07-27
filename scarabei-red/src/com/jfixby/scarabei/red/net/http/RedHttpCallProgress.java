
package com.jfixby.scarabei.red.net.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;

import com.jfixby.scarabei.api.net.http.HttpCallProgress;
import com.jfixby.scarabei.api.net.http.HttpConnection;
import com.jfixby.scarabei.api.strings.Strings;

public class RedHttpCallProgress implements HttpCallProgress {

	private final HttpConnection connection;
	private final byte[] data;

	public RedHttpCallProgress (final HttpConnection connection, final byte[] data) {
		this.connection = connection;
		this.data = data;

	}

	@Override
	public String readResultAsString (final String encoding_string) throws UnsupportedEncodingException {

		final String input_data = Strings.newString(this.data, encoding_string);

		return input_data;
	}

	@Override
	public Object readObject () throws IOException, ClassNotFoundException {
		final ByteArrayInputStream bis = new ByteArrayInputStream(this.data);
		final ObjectInputStream ois = new ObjectInputStream(bis);
		final Object object = ois.readObject();
		return object;
	}

}
