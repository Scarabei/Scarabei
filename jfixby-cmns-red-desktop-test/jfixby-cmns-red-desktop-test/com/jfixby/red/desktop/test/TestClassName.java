
package com.jfixby.red.desktop.test;

import org.junit.Test;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.java.Int;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;

public class TestClassName {

	@Test
	public void test () {
		DesktopSetup.deploy();
		final Int[] array = new Int[10];

		L.d("", JUtils.nameOf(array.getClass()));

	}

}
