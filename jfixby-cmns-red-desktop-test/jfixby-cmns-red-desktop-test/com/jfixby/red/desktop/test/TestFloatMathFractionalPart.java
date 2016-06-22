
package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.red.desktop.DesktopSetup;

public class TestFloatMathFractionalPart {

	public static void main (String[] args) {
		DesktopSetup.deploy();
		{
			double value = 1.93;
			L.d("double", value);
			L.d("FloatMath.fractionalPartOf", FloatMath.fractionalPartOf(value));
			L.d();
		}

	}
}
