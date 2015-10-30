package com.jfixby.red.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;

import com.jfixby.cmns.api.net.http.HttpCallProgress;
import com.jfixby.cmns.api.net.http.HttpConnection;

public class RedHttpCallProgress implements HttpCallProgress {

	private HttpConnection connection;
	private byte[] data;

	public RedHttpCallProgress(HttpConnection connection, byte[] data) {
		this.connection = connection;
		this.data = data;

	}

	public String readResultAsString(String encoding_string)
			throws UnsupportedEncodingException {

		// String input_data = new String(data, "windows-1251");
		String input_data = new String(data, encoding_string);

		return input_data;
	}

	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object object = ois.readObject();
		return object;
	}

}
