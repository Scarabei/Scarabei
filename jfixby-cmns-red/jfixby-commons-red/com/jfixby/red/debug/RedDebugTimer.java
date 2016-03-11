package com.jfixby.red.debug;

import com.jfixby.cmns.api.debug.DebugTimer;
import com.jfixby.cmns.api.log.L;

public class RedDebugTimer implements DebugTimer {
    public RedDebugTimer() {
	reset();
    }

    long startTime;

    @Override
    public void reset() {
	startTime = System.currentTimeMillis();
    }

    @Override
    public void printTime(String string) {
	L.d(string, System.currentTimeMillis() - startTime);
    }

}
