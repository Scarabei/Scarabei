
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

	@Override
	final public void yeld () {
		Thread.yield();
	}

	@Override
	final public boolean sleep (final long period) {
		try {
			Thread.sleep(period);
			return true;
		} catch (final InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	final public void exit () {
		System.out.println("EXIT");
		System.exit(0);
	}

}
