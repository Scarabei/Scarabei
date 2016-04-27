
package com.jfixby.red.err;

import com.jfixby.cmns.api.err.ErrorComponent;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.settings.ExecutionMode;
import com.jfixby.cmns.api.sys.settings.SystemSettings;

public class RedError implements ErrorComponent {

	@Override
	public void reportWarning (final String message) {
		L.e(message);
	}

	@Override
	public void reportError (final String message) {
		if (SystemSettings.executionModeCovers(ExecutionMode.EARLY_DEVELOPMENT)) {
			throw new Error(message);
		} else {
			L.e(message);
		}
	}

	@Override
	public void reportError (final Throwable e) {
		if (SystemSettings.executionModeCovers(ExecutionMode.EARLY_DEVELOPMENT)) {
			throw new Error(e);
		} else {
			L.e(e);
			e.printStackTrace();
		}
	}

	@Override
	public void reportError (final String message, final Throwable e) {
		if (SystemSettings.executionModeCovers(ExecutionMode.EARLY_DEVELOPMENT)) {
			throw new Error(message, e);
		} else {
			L.e(e);
			e.printStackTrace();
		}
	}

}
