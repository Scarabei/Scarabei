
package com.jfixby.cmns.api.lang;

import com.jfixby.cmns.api.java.ByteArray;

public class RedByteArray implements ByteArray {

	final private byte[] bytes;

	public RedByteArray (byte[] bytes) {
		this.bytes = bytes;
	}

	public RedByteArray (int size) {
		this.bytes = new byte[size];
	}

	@Override
	public byte[] toArray () {
		return bytes;
	}

	@Override
	public long size () {
		return bytes.length;
	}

	@Override
	public int getByte (int i) {
		return bytes[i];
	}

}
