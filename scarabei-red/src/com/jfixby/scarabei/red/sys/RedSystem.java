
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.sys.OnExitListener;
import com.jfixby.scarabei.api.sys.SystemComponent;
import com.jfixby.scarabei.api.time.TimeStream;

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
	public boolean isIOS () {
		return OSValidator.isIOS();
	}

	@Override
	final public void yeld () {
		Thread.yield();
	}

	@Override
	final public void wait (final Object lock) {
		Debug.checkNull("lock", lock);
		try {
			lock.wait();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
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

	final List<OnExitListener> onExitListeners = Collections.newList();

	@Override
	final public void exit () {
		for (final OnExitListener listener : this.onExitListeners) {
			listener.onExit();
		}
		System.out.println("EXIT");
		System.exit(0);
	}

	@Override
	public void addOnExitListener (final OnExitListener listener) {
		Debug.checkNull("listener", listener);
		this.onExitListeners.add(listener);
	}

}
