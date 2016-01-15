package com.jfixby.cmns.api.base64;

import java.io.IOException;

import com.jfixby.cmns.api.io.InputStream;

public interface Base64Component {

	String encode(InputStream is) throws IOException;

	String encode(byte[] data) throws IOException;

	byte[] decode(String dataInBase64) throws IOException;

}
