
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.desktop.DesktopSetup;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;

public class TestFloatMathRoundToPoint {

	public static void main (String[] args) {
		DesktopSetup.deploy();
		{
			double value = FloatMath.PI();
			L.d("double", value);
			for (int index = -1; index < 10; index++) {
				L.d("FloatMath.roundToPoint(" + index + ")", FloatMath.roundToDigit(value, index));
			}

		}

	}
}
