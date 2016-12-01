
package com.jfixby.red.err;

import com.jfixby.cmns.api.err.ErrorComponent;
import com.jfixby.cmns.api.err.NotImplementedYetException;
import com.jfixby.cmns.api.log.L;

public class RedError implements ErrorComponent {

	@Override
	public void reportGCLeak (final String message, final Object leakingObject) {
		L.e(message, leakingObject);
	}

	@Override
	public void reportError (final String message) {
		throw new Error(message);
	}

	@Override
	public void reportError (final Throwable e) {
		throw new Error(e);
	}

	@Override
	public void reportError (final String message, final Throwable e) {
		throw new Error(message, e);
	}

	@Override
	public void reportNotImplementedYet () {
		this.reportError(new NotImplementedYetException());
	}

	@Override
	public void reportError (final Thread t, final Throwable e) {
		throw new Error("Thread error: " + t, e);
	}

	@Override
	public void reportWarning (final String msg, final Throwable e) {
		L.e(msg);
		e.printStackTrace();
	}

	@Override
	public void reportWarning (final String message) {
		L.e(message);
	}

}
