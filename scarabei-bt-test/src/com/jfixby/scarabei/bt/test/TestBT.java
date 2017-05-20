
package com.jfixby.scarabei.bt.test;

import java.io.IOException;

import com.jfixby.scarabei.api.bt.ScarabeiBlueTooth;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.bt.red.desktop.RedDesktopBlueTooth;

public class TestBT {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		ScarabeiBlueTooth.installComponent(new RedDesktopBlueTooth());

// final BTSpecs specs = ScarabeiBlueTooth.newBTSpecs();
// final BT bt = ScarabeiBlueTooth.newBT(specs);

// final RemoteDeviceDiscovery disco = new RemoteDeviceDiscovery();
// final Collection<BTDeviceInfo> list = disco.getDevices();
// list.print("list");

// final ServicesSearch search = new ServicesSearch();
// final Map<String, Map<String, String>> devices = search.getBluetoothDevices();
// devices.print("devices");

		final String url = "btspp://98D331B2B6D3:1;authenticate=false;encrypt=false;master=false";
// final InputStream java_stream =;
		final GasSensorMessageReaderSpecs specs = new GasSensorMessageReaderSpecs();
		specs.url = (url);
		final GasSensorMessageReader reader = new GasSensorMessageReader(specs);
		reader.open();

		while (true) {
			final GasSensorMessage message = reader.read();
			message.print();
// Sys.exit();
		}
// is.close();
// final BluetoothAdapter bta = new BluetoothAdapter();

// BluetoothDevice connect_device = mBluetoothAdapter.getRemoteDevice(address);
//
// try {
// BluetoothSocket socket = connect_device.createRfcommSocketToServiceRecord(my_UUID);
// socket.connect();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }

	}

}
