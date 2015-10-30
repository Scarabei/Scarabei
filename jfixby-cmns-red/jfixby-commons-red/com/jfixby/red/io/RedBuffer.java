package com.jfixby.red.io;

import com.jfixby.cmns.api.io.Buffer;

public class RedBuffer implements Buffer {

	private byte[] bytes;

	public RedBuffer(byte[] bytes) {
		this.bytes = bytes;

	}

	public RedBuffer() {
		this.bytes = new byte[0];
	}

	@Override
	public byte[] getBytes() {
		
		return bytes;
	}
}
