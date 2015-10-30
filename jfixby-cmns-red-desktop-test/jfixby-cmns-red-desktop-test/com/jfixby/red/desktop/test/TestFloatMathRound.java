package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;

public class TestFloatMathRound {

	public static void main(String[] args) {
		Setup.setup();
		{
			double value = 0.33;
			L.d("double", value);
			L.d("         (long)", (long) value);
			L.d("FloatMath.round", FloatMath.round(value));
			L.d();
		}
		{
			double value = -0.33;
			L.d("double", value);
			L.d("         (long)", (long) value);
			L.d("FloatMath.round", FloatMath.round(value));
			L.d();
		}
		{
			double value = 0.933;
			L.d("double", value);
			L.d("         (long)", (long) value);
			L.d("FloatMath.round", FloatMath.round(value));
			L.d();
		}
		{
			double value = -0.933;
			L.d("double", value);
			L.d("         (long)", (long) value);
			L.d("FloatMath.round", FloatMath.round(value));
			L.d();
		}

	}
}
