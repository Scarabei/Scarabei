package com.jfixby.scarabei.adopted.gdx.base64;

import java.io.IOException;

import com.badlogic.gdx.utils.Base64Coder;
import com.jfixby.scarabei.api.base64.Base64Component;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.JUtils;

public class GdxBase64 implements Base64Component {

    @Override
    public ByteArray decode(String dataInBase64) throws IOException {
	return JUtils.newByteArray(Base64Coder.decode(dataInBase64));
    }

    @Override
    public String encode(InputStream is) throws IOException {
	return encode(is.readAll());
    }

    @Override
    public String encode(ByteArray bytes) throws IOException {
	return JUtils.newString(Base64Coder.encode(bytes.toArray()));
    }

}
