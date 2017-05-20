
package com.jfixby.scarabei.bt.test;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.log.L;

public class RemoteDeviceDiscovery {
	final List<BTDeviceInfo> list = Collections.newList();

	final Object inquiryCompletedEvent = new Object();
	final DiscoveryListener listener = new DiscoveryListener() {

		@Override
		public void deviceDiscovered (final RemoteDevice btDevice, final DeviceClass cod) {
			/* Get devices paired with system or in range(Without Pair) */
			final BTDeviceInfo dev = new BTDeviceInfo(btDevice, cod);
			RemoteDeviceDiscovery.this.list.add(dev);
// btDevice.toString();
		}

		@Override
		public void inquiryCompleted (final int discType) {
			/* Notify thread when inquiry completed */
			synchronized (RemoteDeviceDiscovery.this.inquiryCompletedEvent) {
				RemoteDeviceDiscovery.this.inquiryCompletedEvent.notifyAll();
			}
		}

		/* To find service on bluetooth */
		@Override
		public void serviceSearchCompleted (final int transID, final int respCode) {
		}

		/* To find service on bluetooth */
		@Override
		public void servicesDiscovered (final int transID, final ServiceRecord[] servRecord) {
		}
	};

	public Collection<BTDeviceInfo> getDevices () throws BluetoothStateException {
		/* Create Vector variable */

		/* Clear Vector variable */
		this.list.clear();

		/* Create an object of DiscoveryListener */

		synchronized (this.inquiryCompletedEvent) {
			/* Start device discovery */
			final boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC,
				this.listener);
			if (started) {
				L.d("wait for device inquiry to complete...");
				try {
					this.inquiryCompletedEvent.wait();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		/* Return list of devices */
		return this.list;
	}
}
