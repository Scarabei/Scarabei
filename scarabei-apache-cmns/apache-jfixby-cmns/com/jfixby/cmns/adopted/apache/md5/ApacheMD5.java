
package com.jfixby.cmns.adopted.apache.md5;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.md5.MD5Component;
import com.jfixby.red.util.md5.RedMD5;

public class ApacheMD5 extends RedMD5 implements MD5Component {

	@Override
	public String md5Stream (final InputStream input_stream) throws IOException {
		Debug.checkNull("input_stream", input_stream);
		final java.io.InputStream jis = input_stream.toJavaInputStream();
		final String result = DigestUtils.md5Hex(jis);
		return result;
	}

}
