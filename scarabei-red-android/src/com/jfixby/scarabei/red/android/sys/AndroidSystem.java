
package com.jfixby.scarabei.red.android.sys;

import com.jfixby.scarabei.android.api.Android;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.red.sys.RedSystem;

public class AndroidSystem extends RedSystem {

	@Override
	public Map<ID, String> getSystemInfo () {
		return Android.getSystemInfo();
	}
}
