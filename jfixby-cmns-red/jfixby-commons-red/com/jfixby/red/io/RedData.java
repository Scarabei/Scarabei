package com.jfixby.red.io;

import com.jfixby.cmns.api.io.Data;

public class RedData implements Data {

	public int integer;

	@Override
	public String toString() {
		return "0x" + Integer.toHexString(integer).toUpperCase();
	}

	@Override
	public char toChar() {
		return (char) integer;
	}

	@Override
	public boolean isEndOfStream() {
		return integer == -1;
	}

	@Override
	public boolean isEndOfLine() {
		return integer == 10;
	}

}
