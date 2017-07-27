
package com.jfixby.scarabei.red.net.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;

import com.jfixby.scarabei.api.net.http.HttpCallProgress;
import com.jfixby.scarabei.api.net.http.HttpConnection;
import com.jfixby.scarabei.api.util.JUtils;

public class RedHttpCallProgress implements HttpCallProgress {

	private HttpConnection connection;
	private byte[] data;

	public RedHttpCallProgress (HttpConnection connection, byte[] data) {
		this.connection = connection;
		this.data = data;

	}

	@Override
	public String readResultAsString (String encoding_string) throws UnsupportedEncodingException {

		String input_data = JUtils.newString(data, encoding_string);

		return input_data;
	}

	@Override
	public Object readObject () throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object object = ois.readObject();
		return object;
	}

}
