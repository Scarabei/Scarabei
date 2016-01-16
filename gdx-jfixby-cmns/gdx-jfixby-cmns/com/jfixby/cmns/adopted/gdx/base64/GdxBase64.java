package com.jfixby.cmns.adopted.gdx.base64;

import java.io.IOException;

import com.badlogic.gdx.utils.Base64Coder;
import com.jfixby.cmns.api.base64.Base64Component;
import com.jfixby.cmns.api.io.InputStream;

public class GdxBase64 implements Base64Component {

	@Override
	public String encode(InputStream is) throws IOException {
		byte[] bytes = is.readAll();
		String result = new String(Base64Coder.encode(bytes));
		return result;
	}

	@Override
	public byte[] decode(String dataInBase64) throws IOException {
		byte[] bytes = Base64Coder.decode(dataInBase64);
		return bytes;
	}

	@Override
	public String encode(byte[] bytes) throws IOException {
		String result = new String(Base64Coder.encode(bytes));
		return result;
	}

}
