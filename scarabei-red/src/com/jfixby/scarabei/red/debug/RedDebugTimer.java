
package com.jfixby.scarabei.red.debug;

import com.jfixby.scarabei.api.debug.DEBUG_TIMER_MODE;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.debug.DebugTimer;
import com.jfixby.scarabei.api.log.L;

public class RedDebugTimer implements DebugTimer {

	private DEBUG_TIMER_MODE mode;

	public RedDebugTimer (DEBUG_TIMER_MODE mode) {
		Debug.checkNull("DEBUG_TIMER_MODE", mode);
		this.mode = mode;
		reset();
	}

	double start_time_milliseconds;
	double start_time_nanoseconds;
	private double value;
	static final double NANOSECONDS_IN_SECOND = 1000000000L;
	static final double MILLISECONDS_IN_SECOND = 1000L;

	@Override
	public final void reset () {
		this.start_time_milliseconds = System.currentTimeMillis();
		this.start_time_nanoseconds = System.nanoTime();
	}

	@Override
	public void printTime (final String string) {
		L.component().d(string, (getTime() * MILLISECONDS_IN_SECOND) + " ms");
	}

	@Override
	public final double getTime () {
		if (this.mode == DEBUG_TIMER_MODE.MILLISECONDS) {
			this.value = (System.currentTimeMillis() - this.start_time_milliseconds) / MILLISECONDS_IN_SECOND;
			return this.value;
		} else {
			this.value = (System.nanoTime() - this.start_time_nanoseconds) / NANOSECONDS_IN_SECOND;
			return this.value;
		}
	}

	@Override
	final public void printTimeAbove (final double threshold, final String tag) {
		if (this.getTime() > threshold) {
			this.printTime(tag);
		}
	}

	@Override
	final public void printTimeAbove (final long threshold, final String tag) {
		this.printTimeAbove(threshold / MILLISECONDS_IN_SECOND, tag);
	}

	@Override
	final public DEBUG_TIMER_MODE getMode () {
		return mode;
	}

}
