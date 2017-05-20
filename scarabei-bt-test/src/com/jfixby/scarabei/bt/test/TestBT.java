
package com.jfixby.scarabei.bt.test;

import com.jfixby.scarabei.api.bt.ScarabeiBlueTooth;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.bt.red.desktop.RedDesktopBlueTooth;

public class TestBT {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();

		ScarabeiBlueTooth.installComponent(new RedDesktopBlueTooth());

	}

}
