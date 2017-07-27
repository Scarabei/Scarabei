
package com.jfixby.scarabei.red.ios.sys;

import com.jfixby.scarabei.api.sys.SystemInfo;
import com.jfixby.scarabei.ios.api.iOS;
import com.jfixby.scarabei.red.sys.RedSystem;

public class iOSSystem extends RedSystem {

	@Override
	public SystemInfo getSystemInfo () {
		return iOS.getSystemInfo();
	}
}
