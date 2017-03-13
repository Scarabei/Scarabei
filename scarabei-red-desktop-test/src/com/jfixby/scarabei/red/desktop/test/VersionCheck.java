
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.ScarabeiAPIVersion;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;

public class VersionCheck {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();
		L.d(ScarabeiAPIVersion.versionString);

	}

}
