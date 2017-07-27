
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.SystemInfo;

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

	public void putAll (final Mapping<? extends String, ? extends String> values) {
		this.params.putAll(values);
	}

}
