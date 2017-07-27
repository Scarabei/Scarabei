
package com.jfixby.scarabei.red.util;

import com.jfixby.scarabei.api.util.BinaryCode;

public class RedBinaryCodeCache {
	private static final int SIZE = 256;
	private static final int MAX_BITS = 8;
	final BinaryCode[][] array = new BinaryCode[SIZE][MAX_BITS];

	RedBinaryCodeCache () {
		for (int s = 1; s <= MAX_BITS; s++) {
			for (int i = 0; i < SIZE; i++) {
				RedBinaryCode form = new RedBinaryCode(i, s);
				array[i][s - 1] = form;
			}
		}
		// L.d();
	}

	public BinaryCode get (final int bits, final int numberOfBits) {
		return this.array[0xff & bits][numberOfBits - 1];
	}

}
