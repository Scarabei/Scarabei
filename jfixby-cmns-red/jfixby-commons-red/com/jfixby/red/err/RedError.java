package com.jfixby.red.err;

import com.jfixby.cmns.api.err.ErrorComponent;
import com.jfixby.cmns.api.log.L;

public class RedError implements ErrorComponent {

	@Override
	public void reportWarning(String message) {
		L.e(message);
	}

	@Override
	public void reportError(String message) {
		throw new Error(message);
	}

}
