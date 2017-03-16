
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.IntegerMath;

public class TestFloatMathLog {

	public static void main (String[] args) {
		ScarabeiDesktop.deploy();
		{
			long value = IntegerMath.power(2L, 10);
			L.d("value", value);
			L.d("FloatMath.log", FloatMath.log(2, value));
			L.d();
		}

	}
}
