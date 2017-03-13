
package com.jfixby.scarabei.api.md5;

import java.io.IOException;

import com.jfixby.scarabei.api.io.InputStream;

public interface MD5Component {

// String md5Stream (java.io.InputStream java_input_stream) throws IOException;

	MD5String md5String (String password);

	MD5String md5Stream (InputStream input_stream) throws IOException;

}
