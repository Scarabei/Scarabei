
package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.Average;
import com.jfixby.cmns.api.math.FloatMath;

public class AverageTest {

	public static void main (final String[] args) {
		DesktopSetup.deploy();

		final Average av = FloatMath.newAverage(10);
		double sum = 0;
		int k = 0;
		for (int i = 0; i < 10; i++) {
			av.addValue(i);
			sum = sum + i;
			k++;
		}
		L.d("sum/k", sum / k);
		L.d("av", av.getAverage());

	}

}
