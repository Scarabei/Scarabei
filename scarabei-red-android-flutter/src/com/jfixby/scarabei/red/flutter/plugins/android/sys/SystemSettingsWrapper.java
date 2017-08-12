
package com.jfixby.scarabei.red.flutter.plugins.android.sys;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

public static class SystemSettingsWrapper {

	public static void setExecutionMode (final ExecutionMode execution_mode) {
		SystemSettings.setExecutionMode(execution_mode);
	}

	public static void setFlag (final ID flag_name, final boolean flag_value) {
		SystemSettings.setFlag(flag_name, flag_value);
	}

	public static boolean getFlag (final ID flag_name) {
		return SystemSettings.getFlag(flag_name);
	}

	public static String getStringParameter (final ID parameter_name, final String defaultValue) {
		return SystemSettings.getStringParameter(parameter_name, defaultValue);
	}

	public static void setStringParameter (final ID parameter_name, final String parameter_value) {
		SystemSettings.setStringParameter(parameter_name, parameter_value);
	}

	public static void setSystemAssetID (final ID parameter_name, final ID parameter_value) {
		SystemSettings.setSystemAssetID(parameter_name, parameter_value);
	}

	public static String getSystemAssetID (final ID parameter_name) {
		return SystemSettings.getSystemAssetID(parameter_name) + "";
	}

	public static boolean executionModeIsAtLeast (final ExecutionMode mode) {
		return SystemSettings.executionModeIsAtLeast(mode);
	}

	public static String getExecutionMode () {
		return SystemSettings.getExecutionMode() + "";
	}

	public static void setIntParameter (final ID parameterName, final long parameterValue) {
		SystemSettings.setIntParameter(parameterName, parameterValue);
	}

	public static long getIntParameter (final ID parameter_name) {
		return SystemSettings.getIntParameter(parameter_name);
	}

	public static java.util.Map<String, Object> listAllSettings () {
		final Map<ID, Object> list = SystemSettings.listAllSettings();
		final Map<String, Object> map = Collections.newMap();
		for (final ID key : list.keys()) {
			map.put(key + "", value(list.get(key)));

		}
		return map.toJavaMap();
	}

	public static Object value (final Object object) {
		if (object instanceof ExecutionMode) {
			return object + "";
		}
		if (object instanceof ID) {
			return object + "";
		}
		return object;
	}

	public static void clearAll () {
		SystemSettings.clearAll();
	}

	public static boolean saveToStorage () {
		try {
			return SystemSettings.saveToStorage().await();
		} catch (final Throwable e) {
			L.e(e);
			return false;
		}
	}

}
