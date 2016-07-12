
package com.jfixby.cmns.api.err;

public class DefaultErrorComponent implements ErrorComponent {

	@Override
	public void reportWarning (final String message) {
		System.err.println(message);
	}

	@Override
	public void reportError (final String message) {
		throw new Error(message);
	}

	@Override
	public void reportError (final Throwable e) {
		e.printStackTrace();
	}

	@Override
	public void reportError (final String message, final Throwable e) {
		throw new Error(message, e);
	}

	@Override
	public void reportNotImplementedYet () {
		this.reportError(new NotImplementedYetException());
	}

}
