
package com.jfixby.scarabei.adopted.apache.md5;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.md5.MD5Component;
import com.jfixby.scarabei.api.md5.MD5String;
import com.jfixby.scarabei.red.util.md5.RedMD5;
import com.jfixby.scarabei.red.util.md5.RedMD5String;

public class ApacheMD5 extends RedMD5 implements MD5Component {

	@Override
	public MD5String md5Stream (final InputStream input_stream) throws IOException {
		Debug.checkNull("input_stream", input_stream);
		final java.io.InputStream jis = input_stream.toJavaInputStream();
		final String result = DigestUtils.md5Hex(jis);
		return new RedMD5String(result);
	}

}
