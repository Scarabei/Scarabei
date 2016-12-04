
package com.jfixby.red.err;

import com.jfixby.cmns.api.err.ErrorComponent;
import com.jfixby.cmns.api.err.NotImplementedYetException;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;

public class RedError implements ErrorComponent {

	@Override
	public void reportGCLeak (final String message, final Object leakingObject) {
		L.e(message, leakingObject);
	}

	@Override
	public void reportError (final String message) {
		L.e(message);
		Sys.exit();
	}

	@Override
	public void reportError (final Throwable e) {
		L.e(e);
		Sys.exit();
	}

	@Override
	public void reportError (final String message, final Throwable e) {
		L.e(message, e);
		Sys.exit();
	}

	@Override
	public void reportError (final Thread t, final Throwable e) {
		L.e("Thread", t);
		L.e(e);
		Sys.exit();
	}

	@Override
	public void reportNotImplementedYet () {
		this.reportError(new NotImplementedYetException());
	}

}
