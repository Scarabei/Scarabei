
package com.jfixby.scarabei.red.desktop.test;

import org.junit.Test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.java.Int;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;

public class TestClassName {

	@Test
	public void test () {
		ScarabeiDesktop.deploy();
		final Int[] array = new Int[10];

		L.d("", JUtils.nameOf(array.getClass()));

	}

}
