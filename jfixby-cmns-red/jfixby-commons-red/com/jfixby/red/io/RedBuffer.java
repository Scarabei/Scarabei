
package com.jfixby.red.io;

import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;

public class RedBuffer implements Buffer {

	private ByteArray bytes;

	public RedBuffer (ByteArray bytes) {
		this.bytes = bytes;

	}

	public RedBuffer () {
		this.bytes = JUtils.newByteArray(0);
	}

	@Override
	public ByteArray getBytes () {
		return bytes;
	}
}
