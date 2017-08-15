
package com.jfixby.scarabei.red.ios.sys;

import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.ios.api.iOS;
import com.jfixby.scarabei.red.sys.RedSystem;

public class iOSSystem extends RedSystem {

	@Override
	public Map<ID, Object> getSystemInfo () {
		return iOS.getSystemInfo();
	}
}
