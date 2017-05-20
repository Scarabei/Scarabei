
package com.jfixby.scarabei.bt.test;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;

public class RemoteDeviceDiscovery {

	public Collection<BTDeviceInfo> getDevices () {
		/* Create Vector variable */
		final List<BTDeviceInfo> list = Collections.newList();
		try {
			final Object inquiryCompletedEvent = new Object();
			/* Clear Vector variable */
			list.clear();

			/* Create an object of DiscoveryListener */
			final DiscoveryListener listener = new DiscoveryListener() {

				@Override
				public void deviceDiscovered (final RemoteDevice btDevice, final DeviceClass cod) {
					/* Get devices paired with system or in range(Without Pair) */
					final BTDeviceInfo dev = new BTDeviceInfo(btDevice, cod);
					list.add(dev);
				}

				@Override
				public void inquiryCompleted (final int discType) {
					/* Notify thread when inquiry completed */
					synchronized (inquiryCompletedEvent) {
						inquiryCompletedEvent.notifyAll();
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

			synchronized (inquiryCompletedEvent) {
				/* Start device discovery */
				final boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
				if (started) {
					System.out.println("wait for device inquiry to complete...");
					inquiryCompletedEvent.wait();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		/* Return list of devices */
		return list;
	}
}
