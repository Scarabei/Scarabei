package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.math.IntegerMath;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class TestFloatMathLog {

	public static void main(String[] args) {
		DesktopAssembler.setup();
		{
			long value = IntegerMath.power(2L, 10);
			L.d("value", value);
			L.d("FloatMath.log", FloatMath.log(2, value));
			L.d();
		}

	}
}
