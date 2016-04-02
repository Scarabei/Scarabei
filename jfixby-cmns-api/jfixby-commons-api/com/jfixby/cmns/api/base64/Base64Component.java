package com.jfixby.cmns.api.base64;

import java.io.IOException;

import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.java.ByteArray;

public interface Base64Component {

    String encode(InputStream is) throws IOException;

    String encode(ByteArray data) throws IOException;

    ByteArray decode(String dataInBase64) throws IOException;

}
