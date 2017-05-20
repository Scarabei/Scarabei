
package com.jfixby.scarabei.bt.test;

import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;

public class ServicesSearch {

	/** UUID used to find specific service supported by bluetooth device
	 * https://www.bluetooth.org/en-us/specification/assigned-numbers/service-discovery Find UUIDs for all types of bluetooth
	 * services. */
	/* To find push object service */
	private final UUID OBEX_OBJECT_PUSH_PROFILE = new UUID(0x1105);
	/* To find file transfer service */
	private final UUID OBEX_FILE_TRANSFER_PROFILE = new UUID(0x1106);
	/* To find hands free service */
	private final UUID HANDS_FREE = new UUID(0x111E);
	/* Get URL attribute from bluetooth service */
	private final int URL_ATTRIBUTE = 0X0100;

	public Map<String, Map<String, String>> getBluetoothDevices () {
		/** Find service on bluetooth device Note: In following line you can use one service at a time. I'm new to bluetooth
		 * programming it might me wrong perception. UUID[] searchUuidSet = new UUID[]{OBEX_FILE_TRANSGER_PROFILE};
		 *
		 * CORRECT: UUID[] searchUuidSet = new UUID[]{OBEX_FILE_TRANSGER_PROFILE}; WRONG: UUID[] searchUuidSet = new
		 * UUID[]{OBEX_FILE_TRANSGER_PROFILE, OBEX_OBJECT_PUSH_PROFILE}; */
		/* Initialize UUID Array */
		final UUID[] searchUuidSet = new UUID[] {this.HANDS_FREE};
		final Object serviceSearchCompletedEvent = new Object();
		final int[] attrIDs = new int[] {this.URL_ATTRIBUTE};

		/* Create an object to get list of devices in range or paired */
		final RemoteDeviceDiscovery remoteDeviceDiscovery = new RemoteDeviceDiscovery();
		/* Create map to return Bluetooth device address, name and URL */
		final Map<String, Map<String, String>> mapReturnResult = Collections.newMap();

		try {
			/* Create an object of DiscoveryListener */
			final DiscoveryListener listener = new DiscoveryListener() {

				/* To find bluetooth devices */
				@Override
				public void deviceDiscovered (final RemoteDevice btDevice, final DeviceClass cod) {
				}

				/* To find bluetooth devices */
				@Override
				public void inquiryCompleted (final int discType) {
				}

				/* Find service URL of bluetooth device */
				@Override
				public void servicesDiscovered (final int transID, final ServiceRecord[] servRecord) {
					for (int i = 0; i < servRecord.length; i++) {
						/* Find URL of bluetooth device */
						final String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
						if (url == null) {
							continue;
						}
						String temporaryString = "";
						/* Get object of bluetooth device */
						final RemoteDevice rd = servRecord[i].getHostDevice();
						/* Get attribute from ServiceRecord */
						final DataElement serviceName = servRecord[i].getAttributeValue(ServicesSearch.this.URL_ATTRIBUTE);
						if (serviceName != null) {
							temporaryString = serviceName.getValue() + "\n" + url;
							/* Put it in map */
							mapReturnResult.get(rd.getBluetoothAddress()).put("serviceName", temporaryString);
						} else {
							temporaryString = "Uknown service \n" + url;
							/* Put it in map */
							mapReturnResult.get(rd.getBluetoothAddress()).put("serviceName", temporaryString);
						}
					}
				}

				@Override
				public void serviceSearchCompleted (final int transID, final int respCode) {
					/* Notify thread when search completed */
					synchronized (serviceSearchCompletedEvent) {
						serviceSearchCompletedEvent.notifyAll();
					}
				}
			};

			/* Get list of bluetooth device from class RemoteDeviceDiscovery */
			for (final BTDeviceInfo dev : remoteDeviceDiscovery.getDevices()) {
				/* Get RemoteDevice object */
				final RemoteDevice btDevice = dev.getRemoteDevice();
				/* Create list to return details */
				final Map<String, String> listDeviceDetails = Collections.newMap();

				try {
					/* Add bluetooth device name and address in list */
					listDeviceDetails.put("name", btDevice.getFriendlyName(true));
					listDeviceDetails.put("BluetoothAddress", btDevice.getBluetoothAddress());
				} catch (final Exception e) {
				}

				/* Put bluetooth device details in map */
				mapReturnResult.put(btDevice.getBluetoothAddress(), listDeviceDetails);
				synchronized (serviceSearchCompletedEvent) {
					LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, listener);
					serviceSearchCompletedEvent.wait();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		/* Return bluetooth devices detail */
		return mapReturnResult;
	}
}
