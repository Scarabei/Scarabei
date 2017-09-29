
package com.jfixby.scarabei.api.sys.settings;

import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;

public enum ExecutionMode {

	EARLY_DEVELOPMENT(3000), //
	TESTING(2000), //
	RELEASE_CANDIDATE(1000), //
	DEMO(500), //
	PUBLIC_RELEASE(0);//

	final private int level;

	ExecutionMode(final int level) {
		this.level = level;

	}

	public static ID ExecutionModeTAG() {
		return Names.newID(ExecutionMode.GLOBAL_TYPE_NAME_STRING);
	}

	static final String GLOBAL_TYPE_NAME_STRING = "com.jfixby.scarabei.api.sys.settings.ExecutionMode";

	public final boolean isAtLeast(final ExecutionMode targetMode) {
		return this.level >= targetMode.level;
	}

	public boolean isBelow(final ExecutionMode targetMode) {
		return this.level < targetMode.level;
	}

	public static ExecutionMode resolve(final String exeString) {
		for (final ExecutionMode ex : ExecutionMode.values()) {
			if (ex.toString().equals(exeString)) {
				return ex;
			}
		}
		L.e("Failed to resolve ExecutionMode", "<" + exeString + ">");
		final ExecutionMode defaultMode = ExecutionMode.PUBLIC_RELEASE;
		L.e("           setting default mode", defaultMode);
		return defaultMode;
	}

}
