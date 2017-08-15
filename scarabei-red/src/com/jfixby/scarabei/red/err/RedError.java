
package com.jfixby.scarabei.red.err;

import com.jfixby.scarabei.api.err.ErrorComponent;
import com.jfixby.scarabei.api.err.NotImplementedYetException;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.Sys;

public class RedError implements ErrorComponent {
	public static final long EXIT_DELAY = 100;

	@Override
	public void reportGCLeak (final String message, final Object leakingObject) {
		L.e(message, leakingObject);
	}

	@Override
	public void reportError (final String message) {
		final Error e = new Error(message);
		L.e(e);
		this.sleep(EXIT_DELAY);
		this.exit();
	}

	@Override
	public void reportError (final Throwable e) {
		L.e(e);
		this.sleep(EXIT_DELAY);
		this.exit();
	}

	@Override
	public void reportError (final String message, final Throwable e) {
		L.e(message, e);
		this.sleep(EXIT_DELAY);
		this.exit();
	}

	@Override
	public void reportError (final Thread t, final Throwable e) {
		L.e("Thread", t);
		L.e(e);
		this.sleep(EXIT_DELAY);
		this.exit();
	}

	private void exit () {
		if (Sys.component() != null) {
			Sys.exit();
		} else {
			System.exit(0);
		}
	}

	private void sleep (final long exitDelay) {
		if (Sys.component() != null) {
			Sys.sleep(exitDelay);
		} else {
			try {
				Thread.sleep(exitDelay);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void reportNotImplementedYet () {
		L.e(new NotImplementedYetException());
		this.sleep(EXIT_DELAY);
		this.exit();
	}

}
