
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.time.TimeStream;

public class NoClock implements TimeStream {
	@Override
	public String toString () {
		return "NoClock[StaticTime]";
	}

	@Override
	public long currentTimeMillis () {
// new Exception().printStackTrace();
		return 0;
	}
}
