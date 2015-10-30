package com.jfixby.red.util.md5;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.md5.MD5Component;

public abstract class RedMD5 implements MD5Component {

	@Override
	public String md5String(String password) {
		JUtils.checkNull("input", password);
		try {
			ByteArrayInputStream buff = new ByteArrayInputStream(
					password.getBytes());
			buff.close();
			String result = md5Stream(buff);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
