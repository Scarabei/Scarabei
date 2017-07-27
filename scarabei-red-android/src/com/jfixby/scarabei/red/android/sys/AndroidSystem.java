
package com.jfixby.scarabei.red.android.sys;

import com.jfixby.scarabei.android.api.Android;
import com.jfixby.scarabei.api.sys.SystemInfo;
import com.jfixby.scarabei.red.sys.RedSystem;

public class AndroidSystem extends RedSystem {

	@Override
	public SystemInfo getSystemInfo () {
		return Android.getSystemInfo();
	}
}
