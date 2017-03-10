
package com.jfixby.scarabei.api.base64;

import java.io.IOException;

import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.java.ByteArray;

public interface Base64Component {

	String encode (InputStream is) throws IOException;

	String encode (ByteArray data) throws IOException;

	ByteArray decode (String dataInBase64) throws IOException;

}
