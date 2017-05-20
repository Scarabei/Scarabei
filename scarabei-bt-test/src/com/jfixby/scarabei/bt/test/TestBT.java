
package com.jfixby.scarabei.bt.test;

import com.jfixby.scarabei.api.bt.ScarabeiBlueTooth;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.bt.red.desktop.RedDesktopBlueTooth;

public class TestBT {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();
		ScarabeiBlueTooth.installComponent(new RedDesktopBlueTooth());

// final BTSpecs specs = ScarabeiBlueTooth.newBTSpecs();
// final BT bt = ScarabeiBlueTooth.newBT(specs);
// final RemoteDeviceDiscovery disco = new RemoteDeviceDiscovery();
// final Collection<BTDeviceInfo> list = disco.getDevices();
// list.print("list");

		final ServicesSearch search = new ServicesSearch();
		final Map<String, Map<String, String>> devices = search.getBluetoothDevices();
		devices.print("devices");

	}

}
