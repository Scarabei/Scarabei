
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
		Sys.sleep(EXIT_DELAY);
		Sys.exit();
	}

	@Override
	public void reportError (final Throwable e) {
		L.e(e);
		Sys.sleep(EXIT_DELAY);
		Sys.exit();
	}

	@Override
	public void reportError (final String message, final Throwable e) {
		L.e(message, e);
		Sys.sleep(EXIT_DELAY);
		Sys.exit();
	}

	@Override
	public void reportError (final Thread t, final Throwable e) {
		L.e("Thread", t);
		L.e(e);
		Sys.sleep(EXIT_DELAY);
		Sys.exit();
	}

	@Override
	public void reportNotImplementedYet () {
		L.e(new NotImplementedYetException());
		Sys.sleep(EXIT_DELAY);
		Sys.exit();
	}

}
