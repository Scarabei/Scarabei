package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class PrimeSearch {

	public static final int offset = 0;
	public static final boolean NOT_PRIME = true;

	public static void main(String[] args) {
		DesktopAssembler.setup();
		long N = Integer.MAX_VALUE;

		L.d("array size", N * 1.0f / 1024 / 1024 / 1024 + " GB");

		BooleanArray array = new BooleanArray(N);
		long prime = 1;
		for (int i = 2; i < N; i++) {
			if (array.get(i) == false) {
				L.d("prime[" + prime + "]", i);
				prime++;
				long val = 2 * i;
				for (long k = 2; val < N;) {
					array.set((int)val, NOT_PRIME);
					k++;
					val = i * k;
				}
			}

		}

	}

	public static final int indexToNumber(int index) {
		return index + offset;
	}

	public static final int numberToIndex(int number) {
		return number - offset;
	}

}
