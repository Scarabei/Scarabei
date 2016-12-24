
package com.jfixby.scarabei.red.io;

import com.jfixby.scarabei.api.io.Data;

public class RedData implements Data {

	public int integer;

	@Override
	public String toString () {
		return "0x" + Integer.toHexString(this.integer).toUpperCase();
	}

	@Override
	public char toChar () {
		return (char)this.integer;
	}

	@Override
	public boolean isEndOfStream () {
		return this.integer == -1;
	}

	@Override
	public boolean isEndOfLine () {
		return this.integer == 10;
	}

	@Override
	public int toInt () {
		return this.integer;
	}

}
