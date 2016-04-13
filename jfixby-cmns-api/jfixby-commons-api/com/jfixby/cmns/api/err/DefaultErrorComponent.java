
package com.jfixby.cmns.api.err;

public class DefaultErrorComponent implements ErrorComponent {

	@Override
	public void reportWarning (String message) {
		System.err.println(message);
	}

	@Override
	public void reportError (String message) {
		throw new Error(message);
	}

	@Override
	public void reportError (Throwable e) {
		e.printStackTrace();
	}

}
