package com.jfixby.cmns.adopted.apache.md5;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.md5.MD5Component;
import com.jfixby.red.util.md5.RedMD5;

public class ApacheMD5 extends RedMD5 implements MD5Component {

	@Override
	public String md5Stream(java.io.InputStream java_input_stream)
			throws IOException {
		return DigestUtils.md5Hex(java_input_stream);
	}

	@Override
	public String md5Stream(InputStream input_stream) throws IOException {
		JUtils.checkNull("input_stream", input_stream);
		java.io.InputStream jis = input_stream.toJavaInputStream();
		String result = this.md5Stream(jis);
		input_stream.close();
		return result;
	}

}
