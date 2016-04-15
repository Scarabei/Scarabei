
package com.jfixby.red.sys;

import com.jfixby.cmns.api.time.TimeStream;

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
