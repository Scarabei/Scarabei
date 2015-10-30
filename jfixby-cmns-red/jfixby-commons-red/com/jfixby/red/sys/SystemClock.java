package com.jfixby.red.sys;

import com.jfixby.cmns.api.time.TimeStream;

public class SystemClock implements TimeStream {

	@Override
	public String toString() {
		return "SystemTime[" + this.currentTimeMillis() + "]";
	}

	@Override
	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}

}
