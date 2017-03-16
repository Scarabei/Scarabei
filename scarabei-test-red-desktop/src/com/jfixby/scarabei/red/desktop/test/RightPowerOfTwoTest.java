
package com.jfixby.scarabei.red.desktop.test;

import org.junit.Assert;
import org.junit.Test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.IntegerMath;

public class RightPowerOfTwoTest {

	@Test
	public void test () {
		ScarabeiDesktop.deploy();
		int n = 10;
		for (int pot = 0; pot < n; pot++) {
			long potvalueleft = pot(pot);
			long potvalueright = pot(pot + 1);
			for (long k = potvalueleft; k < potvalueright; k++) {
				long k_size = IntegerMath.rightPowerOfTwo(k);
				String msg = k + " rightPowerOfTwo=" + k_size;
				Assert.assertTrue(msg, k_size == pot + 1);
				L.d(msg);
			}
		}

	}

	private long pot (int pot) {
		return IntegerMath.component().powerOfTwo(pot);
	}

}
