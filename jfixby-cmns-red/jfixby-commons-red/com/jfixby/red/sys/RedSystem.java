package com.jfixby.red.sys;

import com.jfixby.cmns.api.sys.SystemComponent;
import com.jfixby.cmns.api.time.TimeStream;

public abstract class RedSystem implements SystemComponent {

    // private final RedSystemSettings settings = new RedSystemSettings();
    // private final RedTaskManager taskman = new RedTaskManager();

    public RedSystem() {

    }

    static final private SystemClock system_clock = new SystemClock();
    static final private NoClock no_clock = new NoClock();

    @Override
    public TimeStream SystemTime() {
	return system_clock;
    }

    @Override
    public TimeStream NoTime() {
	return no_clock;
    }

}
