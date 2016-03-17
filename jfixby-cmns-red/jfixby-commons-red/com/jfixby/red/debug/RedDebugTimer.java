package com.jfixby.red.debug;

import com.jfixby.cmns.api.debug.DebugTimer;
import com.jfixby.cmns.api.log.L;

public class RedDebugTimer implements DebugTimer {
    public RedDebugTimer() {
	reset();
    }

    long startTime;
    private long value;

    @Override
    public void reset() {
	startTime = System.currentTimeMillis();
    }

    @Override
    public void printTime(final String string) {
	L.component().d(string, getTime());
    }

    @Override
    public final long getTime() {
	this.value = System.currentTimeMillis() - startTime;
	return this.value;
    }

    @Override
    public void printTimeAbove(long threshold, String tag) {
	if (this.getTime() > threshold) {
	    this.printTime(tag);
	}
    }

}
