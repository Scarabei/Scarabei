
package com.jfixby.scarabei.red.flutter.plugins.android.sys;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;

public class FlutterMethodCallArgument {
	public String argumentName;
	public Object argumentValue;
	public String argumentType;

	final public Class<?> klass () {
		if ("String".equals(this.argumentType)) {
			return String.class;
		}
		if ("int".equals(this.argumentType)) {
			return Long.class;
		}
		if ("bool".equals(this.argumentType)) {
			return Boolean.class;
		}
		if ("ID".equals(this.argumentType)) {
			return ID.class;
		}
		if ("ExecutionMode".equals(this.argumentType)) {
			return ExecutionMode.class;
		}
		Err.reportError("Unknown class< " + this.argumentType + ">");
		return null;

	}

	public Object argumentValue () {
		if (this.klass() == ID.class) {
			return Names.newID(this.argumentValue + "");
		}
		if (this.klass() == ExecutionMode.class) {
			return ExecutionMode.resolve(this.argumentValue + "");
		}
		return this.argumentValue;
	}

}
