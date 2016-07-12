
package com.jfixby.cmns.api.lang;

import com.jfixby.cmns.api.java.ByteArray;

public class RedByteArray implements ByteArray {

	final private byte[] bytes;

	public RedByteArray (final byte[] bytes) {
		this.bytes = bytes;
	}

	public RedByteArray (final int size) {
		this.bytes = new byte[size];
	}

	@Override
	public byte[] toArray () {
		return this.bytes;
	}

	@Override
	public long size () {
		return this.bytes.length;
	}

	@Override
	public int getByte (final int i) {
		return this.bytes[i];
	}

}
