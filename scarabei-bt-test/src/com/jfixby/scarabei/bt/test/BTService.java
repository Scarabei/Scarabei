
package com.jfixby.scarabei.bt.test;

import javax.bluetooth.DataElement;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;

public class BTService {

	private final ServiceRecord legacy;
	final Map<String, String> values = Collections.newMap();

	public BTService (final ServiceRecord legacy) {
		this.legacy = legacy;
		this.values.put("getHostDevice", "" + legacy.getHostDevice());
		final DataElement serviceName = legacy.getAttributeValue(0x0100);
		this.values.put("serviceName", "" + serviceName);

	}

	@Override
	public String toString () {
		return "BTService" + this.values + "";
	}

	public String getConnectionURL (final int noauthenticateNoencrypt, final boolean b) {// int requiredSecurity, boolean
																														// mustBeMaster
		return this.legacy.getConnectionURL(noauthenticateNoencrypt, b);
	}

	public RemoteDevice getHostDevice () {
		return this.legacy.getHostDevice();
	}

	public DataElement getAttributeValue (final int url_ATTRIBUTE) {
		return this.legacy.getAttributeValue(url_ATTRIBUTE);
	}

}
