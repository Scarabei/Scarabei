package com.jfixby.red.err;

import com.jfixby.cmns.api.err.ErrorComponent;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;

public class RedError implements ErrorComponent {

	@Override
	public void reportWarning(String message) {
		L.e(message);
	}

	@Override
	public void reportError(String message) {
		throw new Error(message);
	}

	@Override
	public void reportError(Throwable e) {
		e.printStackTrace();
		Sys.exit();
	}

}
