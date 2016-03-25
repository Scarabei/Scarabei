package com.jfixby.red.util;

import com.jfixby.cmns.api.util.BitForm;

public class BitCache {
    private static final int SIZE = 256;
    private static final int MAX_BITS = 8;
    final BitForm[][] array = new BitForm[SIZE][MAX_BITS];

    BitCache() {
	for (int s = 0; s < MAX_BITS; s++) {
	    for (int i = 0; i < SIZE; i++) {
		RedBitForm form = new RedBitForm(i, s + 1);
		array[i][s] = form;
	    }
	}
    }

    public BitForm get(final int bits, final int numberOfBits) {

	return this.array[0xff & bits][numberOfBits - 1];
    }

}
