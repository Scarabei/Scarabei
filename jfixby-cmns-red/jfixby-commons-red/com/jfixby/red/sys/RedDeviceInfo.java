
package com.jfixby.red.sys;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.SystemInfo;

public class RedDeviceInfo implements SystemInfo {
	final Map<String, String> params = Collections.newMap();

	@Override
	public Map<String, String> listParameters () {
		return this.params;
	}

	@Override
	public void putValue (final String key, final Object value) {
		this.params.put(key, "" + value);
	}

	@Override
	public void print () {
		L.d("---[DeviceInfo]------------------------");
		this.params.print("params");
	}

}
