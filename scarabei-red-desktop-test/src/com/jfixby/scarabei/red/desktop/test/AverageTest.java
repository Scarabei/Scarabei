
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.Average;
import com.jfixby.scarabei.api.math.FloatMath;

public class AverageTest {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();

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
