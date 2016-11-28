
package com.jfixby.red.android.sys;

import com.jfixby.android.api.Android;
import com.jfixby.cmns.api.sys.SystemInfo;
import com.jfixby.red.sys.RedSystem;

public class AndroidSystem extends RedSystem {

	@Override
	public SystemInfo getSystemInfo () {
		return Android.getSystemInfo();
	}
}
