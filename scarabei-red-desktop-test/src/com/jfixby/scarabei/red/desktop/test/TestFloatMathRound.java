
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;

public class TestFloatMathRound {

	public static void main (String[] args) {
		ScarabeiDesktop.deploy();
		{
			double value = 0.33;
			L.d("double", value);
			L.d("         (long)", (long)value);
			L.d("FloatMath.round", FloatMath.round(value));
			L.d();
		}
		{
			double value = -0.33;
			L.d("double", value);
			L.d("         (long)", (long)value);
			L.d("FloatMath.round", FloatMath.round(value));
			L.d();
		}
		{
			double value = 0.933;
			L.d("double", value);
			L.d("         (long)", (long)value);
			L.d("FloatMath.round", FloatMath.round(value));
			L.d();
		}
		{
			double value = -0.933;
			L.d("double", value);
			L.d("         (long)", (long)value);
			L.d("FloatMath.round", FloatMath.round(value));
			L.d();
		}

	}
}
