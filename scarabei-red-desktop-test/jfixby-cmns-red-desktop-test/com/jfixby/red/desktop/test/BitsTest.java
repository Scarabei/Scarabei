
package com.jfixby.red.desktop.test;

import org.junit.Test;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.BinaryCode;
import com.jfixby.cmns.api.util.JUtils;

public class BitsTest {

	@Test
	public void test () {
		DesktopSetup.deploy();
		for (int i = 0; i < 32 + 0 * 256 * 256; i++) {
			BinaryCode bitform = JUtils.binaryCodeOf(i, 4);
			L.d(i + "", bitform + " vs " + Integer.toBinaryString(i));
		}

	}

}
