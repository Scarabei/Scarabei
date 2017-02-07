
package com.jfixby.scarabei.red.desktop.test;

import java.io.File;

import com.jfixby.scarabei.api.desktop.DesktopSetup;
import com.jfixby.scarabei.api.log.L;

public class TestChar {

	public static void main (final String[] args) {
		DesktopSetup.deploy();

		final String input = "A\\\\B\\\\C\\\\D\\\\E\\\\F\\\\G\\\\H\\\\";
		L.d("input", input);

// final String result = input.replaceAll("/", File.separatorChar + "");
		final String result = input.replaceAll("\\\\", "" + File.separatorChar + File.separatorChar);

		L.d("result", result);

	}

}
