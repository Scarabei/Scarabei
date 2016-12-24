
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.time.TimeStream;

public class SystemClock implements TimeStream {

	@Override
	public String toString () {
		return "SystemTime[" + this.currentTimeMillis() + "]";
	}

	@Override
	public long currentTimeMillis () {
		return System.currentTimeMillis();
	}

}
