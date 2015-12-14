package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class PrimeSearch {

	public static final int offset = 0;
	public static final boolean NOT_PRIME = true;

	public static void main(String[] args) {
		DesktopAssembler.setup();
		int N = Integer.MAX_VALUE / 2;

		L.d("array size", N * 1.0f / 1024 / 1024 / 1024 + " GB");

		final boolean[] array = new boolean[N];
		int prime = 1;
		for (int i = 2; i < N; i++) {
			if (array[i] == false) {
				L.d("prime[" + prime + "]", i);
				prime++;
				int val = 2 * i;
				for (int k = 2; val < N;) {
					array[val] = NOT_PRIME;
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
