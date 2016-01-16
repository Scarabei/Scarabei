package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class TestFloatMathFractionalPart {

	public static void main(String[] args) {
		DesktopAssembler.setup();
		{
			double value = 1.93;
			L.d("double", value);
			L.d("FloatMath.fractionalPartOf", FloatMath.fractionalPartOf(value));
			L.d();
		}

	}
}
