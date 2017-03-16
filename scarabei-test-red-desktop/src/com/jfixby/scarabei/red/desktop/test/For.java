
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.desktop.test.LambdaArray.λ;

public class For {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();

		for (int i = 10; i-- > 0;) {
			L.d("" + i);
		}
		final λ max = (a, b) -> a > b ? a : b;
	}

	public static final int max (final int a, final int b) {
		if (a > b) {
			return a;
		}
		return b;
	}

}
