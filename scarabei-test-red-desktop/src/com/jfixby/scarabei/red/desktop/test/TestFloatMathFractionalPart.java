
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;

public class TestFloatMathFractionalPart {

	public static void main (String[] args) {
		ScarabeiDesktop.deploy();
		{
			double value = 1.93;
			L.d("double", value);
			L.d("FloatMath.fractionalPartOf", FloatMath.fractionalPartOf(value));
			L.d();
		}

	}
}
