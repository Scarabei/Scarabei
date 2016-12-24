
package com.jfixby.scarabei.red.util;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.BinaryCode;
import com.jfixby.scarabei.api.util.EditableBinaryCode;
import com.jfixby.scarabei.api.util.JUtils;

public class RedBinaryCode implements BinaryCode, EditableBinaryCode {

	final StringBuilder bits = new StringBuilder();;

	@Override
	public EditableBinaryCode copy () {
		return new RedBinaryCode(bits);
	}

	public RedBinaryCode (int bits, int size) {
		if (size > 8) {
			Err.reportError("Not sure about that: size=" + size);
		}

		String string = Integer.toBinaryString(bits);
		if (string.length() > size) {
			string = string.substring(string.length() - size, string.length());
		}
		while (string.length() < size) {
			string = (charOf(BinaryCode.ZERO)) + string;
		}
		this.bits.append(string);
		if (this.size() != size) {
			Err.reportError("Size mismatch: " + this.bits + " bits=" + bits + " size=" + size);
		}
		// L.d(bits + "", this);
	}

	@Override
	public String toString () {
		return bits.toString();
	}

	public RedBinaryCode () {
	}

	public RedBinaryCode (String bits) {
		this.bits.append(bits);
	}

	public RedBinaryCode (StringBuilder other_bits) {
		this.bits.append(other_bits);
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bits == null) ? 0 : bits.toString().hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		RedBinaryCode other = (RedBinaryCode)obj;
		if (bits == null) {
			if (other.bits != null) return false;
		} else if (!bits.toString().equals(other.bits.toString())) return false;
		return true;
	}

	@Override
	public void append (final BinaryCode bitform) {
		RedBinaryCode form = (RedBinaryCode)bitform;
		this.bits.append(form.bits);

	}

	@Override
	public void insertAt (BinaryCode bitform, int place) {
		Debug.component().checkNull("BinaryCode", bitform);
		RedBinaryCode form = (RedBinaryCode)bitform;
		this.bits.insert(place, form.bits);
	}

	@Override
	public void append (boolean bit) {
		bits.append(charOf(bit));
	}

	static final private char charOf (final boolean bit) {
		if (bit == BinaryCode.ONE) return '1';
		return '0';
	}

	@Override
	public boolean getBit (int i) {
		return bits.charAt(i) == charOf(ONE);
	}

	@Override
	public int size () {
		return bits.length();
	}

	@Override
	public int retrieveByte () {
		if (this.size() < 8) {
			Err.reportError("No enough bits to retrive byte");
		}
		final String section = this.bits.substring(0, 8);
		final int value = Integer.parseInt(section, 2);
		this.bits.delete(0, 8);
		return value;
	}

	@Override
	public EditableBinaryCode append (int bits, int numberOfBits) {
		final int originalSize = this.size();
		while (numberOfBits > 8) {
			final BinaryCode cached = JUtils.component().binaryCodeOf(bits, 8);
			this.insertAt(cached, originalSize);
			bits = bits >> 8;
			numberOfBits = numberOfBits - 8;
		}

		final BinaryCode cached = JUtils.component().binaryCodeOf(bits, numberOfBits);
		if (cached == null) {
			L.d("bits=" + bits);
			L.d("numberOfBits=" + numberOfBits);
			Err.reportError("Cached value not found");
		}
		this.insertAt(cached, originalSize);

		return this;
	}

	@Override
	public int retrieveBits (int numberOfBits) {
		final int bits = Integer.parseInt(this.bits.substring(0, numberOfBits), 2);
		this.bits.delete(0, numberOfBits);
		return bits;
	}

}
