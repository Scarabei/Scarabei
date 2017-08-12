
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.sys.SystemInfo;

public class RedDeviceInfo implements SystemInfo {
	final Map<ID, String> params = Collections.newMap();

	@Override
	public Map<ID, String> listParameters () {
		return this.params;
	}

	@Override
	public void putValue (final ID key, final Object value) {
		this.params.put(key, "" + value);
	}

	public void putAll (final Mapping<ID, ? extends String> values) {
		this.params.putAll(values);
	}

	@Override
	public String toString () {
		return "DeviceInfo" + this.params + "";
	}

}
