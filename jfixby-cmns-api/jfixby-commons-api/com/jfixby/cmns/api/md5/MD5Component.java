package com.jfixby.cmns.api.md5;

import java.io.IOException;

import com.jfixby.cmns.api.io.InputStream;


public interface MD5Component {

	String md5Stream(java.io.InputStream java_input_stream) throws IOException;

	String md5String(String password);

	String md5Stream(InputStream input_stream)
			throws IOException;

}
