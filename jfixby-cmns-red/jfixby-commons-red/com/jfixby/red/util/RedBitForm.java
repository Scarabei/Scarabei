package com.jfixby.red.util;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.util.BinaryCode;
import com.jfixby.cmns.api.util.JUtils;

public class RedBitForm implements BinaryCode {

    final StringBuffer bits = new StringBuffer();;

    public RedBitForm(int bits, int size) {
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
    public String toString() {
	return bits.toString();
    }

    public RedBitForm() {
    }

    @Override
    public void append(final BinaryCode bitform) {
	RedBitForm form = (RedBitForm) bitform;
	this.bits.append(form.bits);

    }

    @Override
    public void insertAt(BinaryCode bitform, int place) {
	RedBitForm form = (RedBitForm) bitform;
	this.bits.insert(place, form.bits);
    }

    @Override
    public void append(boolean bit) {
	bits.append(charOf(bit));
    }

    static final private char charOf(final boolean bit) {
	if (bit == BinaryCode.ONE)
	    return '1';
	return '0';
    }

    @Override
    public boolean getBit(int i) {
	return bits.charAt(i) == charOf(ONE);
    }

    @Override
    public int size() {
	return bits.length();
    }

    @Override
    public int retrieveByte() {
	if (this.size() < 8) {
	    Err.reportError("No enough bits to retrive byte");
	}
	final int value = Integer.parseInt(this.bits.substring(0, 8));
	this.bits.delete(0, 8);
	return value;
    }

    @Override
    public BinaryCode append(int bits, int numberOfBits) {
	final int originalSize = this.size();
	while (numberOfBits > 8) {
	    final BinaryCode cached = JUtils.component().binaryCodeOf(bits, 8);
	    this.insertAt(cached, originalSize);
	    bits = bits >> 8;
	    numberOfBits = numberOfBits - 8;
	}

	final BinaryCode cached = JUtils.component().binaryCodeOf(bits, numberOfBits);
	this.insertAt(cached, originalSize);
	return this;
    }

}
