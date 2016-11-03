
package com.jfixby.red.sys;

import com.jfixby.cmns.api.sys.SystemComponent;
import com.jfixby.cmns.api.time.TimeStream;

public abstract class RedSystem implements SystemComponent {

	public RedSystem () {

	}

	static final private SystemClock system_clock = new SystemClock();
	static final private NoClock no_clock = new NoClock();

	@Override
	public TimeStream SystemTime () {
		return system_clock;
	}

	@Override
	public TimeStream NoTime () {
		return no_clock;
	}

	@Override
	public boolean isWindows () {
		return OSValidator.isWindows();
	}

	@Override
	public boolean isUnix () {
		return OSValidator.isUnix();
	}

	@Override
	public boolean isMac () {
		return OSValidator.isMac();
	}

}
