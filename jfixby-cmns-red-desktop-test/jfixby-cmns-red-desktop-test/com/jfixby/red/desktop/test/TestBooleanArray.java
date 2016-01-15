package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class TestBooleanArray {

	public static void main(String[] args) {
		DesktopAssembler.setup();
		int N = 170;
		BooleanArray array = new BooleanArray(N);

		for (int i = 0; i < N; i++) {
			array.set(i, false);
		}

		L.d("array", array);

	}

}
