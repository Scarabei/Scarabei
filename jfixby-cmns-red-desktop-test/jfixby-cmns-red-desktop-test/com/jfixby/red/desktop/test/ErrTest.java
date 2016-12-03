
package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.desktop.DesktopSetup;

public class ErrTest {

	public static void main (final String[] args) {
		DesktopSetup.deploy();

		L.e("some problem", new Error("some problem stack"));

		Debug.checkNull("param", null);
	}

}
