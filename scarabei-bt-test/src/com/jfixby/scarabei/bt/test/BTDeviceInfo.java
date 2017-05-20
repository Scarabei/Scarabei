
package com.jfixby.scarabei.bt.test;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

public class BTDeviceInfo {

	private final DeviceClass cod;
	private final RemoteDevice btDevice;

	@Override
	public String toString () {
		return "BTDeviceInfo [cod=" + this.cod + ", btDevice=" + this.btDevice + "]";
	}

	public BTDeviceInfo (final RemoteDevice btDevice, final DeviceClass cod) {
		this.cod = cod;
		this.btDevice = btDevice;
	}

	public RemoteDevice getRemoteDevice () {
		return this.btDevice;
	}

}
