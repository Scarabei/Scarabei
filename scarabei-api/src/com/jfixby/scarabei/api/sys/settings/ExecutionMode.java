
package com.jfixby.scarabei.api.sys.settings;

import com.jfixby.scarabei.api.log.L;

public enum ExecutionMode {

	EARLY_DEVELOPMENT(3000), //
	TESTING(2000), //
	RELEASE_CANDIDATE(1000), //
	DEMO(500), //
	PUBLIC_RELEASE(0);//

	final private int level;

	ExecutionMode (final int level) {
		this.level = level;

	}

	public static String TAG = "com.jfixby.scarabei.ExecutionMode";

	public final boolean isAtLeast (final ExecutionMode targetMode) {
		return this.level >= targetMode.level;
	}

	public boolean isBelow (final ExecutionMode targetMode) {
		return this.level < targetMode.level;
	}

	public static ExecutionMode resolve (final String exeString) {
		for (final ExecutionMode ex : ExecutionMode.values()) {
			if (ex.toString() == exeString) {
				return ex;
			}
		}
		L.e("Failed to resolve ExecutionMode", "[" + exeString + "]");
		final ExecutionMode defaultMode = ExecutionMode.PUBLIC_RELEASE;
		L.e("           setting default mode", defaultMode);
		return defaultMode;
	}

}
