
package com.jfixby.scarabei.red.util;

import java.util.ArrayList;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.java.ByteArray;

public class RedByteArray implements ByteArray {

	final private ArrayList<Byte> bytes = new ArrayList<Byte>();

	@Override
	public String toString () {
		return "ByteArray(" + this.bytes.size() + ") " + (this.bytes) + "";
	}

	public RedByteArray (final byte[] bytes) {
		Debug.checkNull("bytes", bytes);
		for (final Byte b : bytes) {
			this.bytes.add(b);
		}
	}

	public RedByteArray (final int size) {
		for (int i = 0; i < size; i++) {
			this.bytes.add((byte)0);
		}
	}

	@Override
	public byte[] toArray () {
		if ((int)this.size() != this.size()) {
			Err.reportError("Array is too big: " + this.size());
		}
		final byte[] result = new byte[(int)this.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = this.bytes.get(i);
		}
		return result;
	}

	@Override
	public long size () {
		return this.bytes.size();
	}

	@Override
	public int getByte (final int i) {
		return this.bytes.get(i);
	}

	@Override
	public void append (final ByteArray bytes) {
		Debug.checkNull("bytes", bytes);
		for (int i = 0; i < bytes.size(); i++) {
			final byte b = (byte)bytes.getByte(i);
			this.bytes.add(b);
		}
	}

}
