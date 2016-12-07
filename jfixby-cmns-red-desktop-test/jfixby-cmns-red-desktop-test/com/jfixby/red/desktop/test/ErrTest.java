
package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.log.L;

public class ErrTest {

	public static void main (final String[] args) {
		DesktopSetup.deploy();

		L.e("some problem", new Error("some problem stack"));

		Debug.checkNull("param", null);
	}

}
