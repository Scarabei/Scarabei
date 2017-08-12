
package com.jfixby.scarabei.red.flutter.plugins.android.sys;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

public class SystemSettingsWrapper {

	public void setExecutionMode (final ExecutionMode execution_mode) {
		SystemSettings.setExecutionMode(execution_mode);
	}

	public void setFlag (final ID flag_name, final boolean flag_value) {
		SystemSettings.setFlag(flag_name, flag_value);
	}

	public boolean getFlag (final ID flag_name) {
		return SystemSettings.getFlag(flag_name);
	}

	public String getStringParameter (final ID parameter_name, final String defaultValue) {
		return SystemSettings.getStringParameter(parameter_name, defaultValue);
	}

	public void setStringParameter (final ID parameter_name, final String parameter_value) {
		SystemSettings.setStringParameter(parameter_name, parameter_value);
	}

	public void setSystemAssetID (final ID parameter_name, final ID parameter_value) {
		SystemSettings.setSystemAssetID(parameter_name, parameter_value);
	}

	public String getSystemAssetID (final ID parameter_name) {
		return SystemSettings.getSystemAssetID(parameter_name) + "";
	}

	public boolean executionModeIsAtLeast (final ExecutionMode mode) {
		return SystemSettings.executionModeIsAtLeast(mode);
	}

	public String getExecutionMode () {
		return SystemSettings.getExecutionMode() + "";
	}

	public void setIntParameter (final ID parameterName, final long parameterValue) {
		SystemSettings.setIntParameter(parameterName, parameterValue);
	}

	public long getIntParameter (final ID parameter_name) {
		return SystemSettings.getIntParameter(parameter_name);
	}

	public Map<ID, Object> listAllSettings () {
		return null;
	}

	public void clearAll () {
		SystemSettings.clearAll();
	}

	public boolean saveToStorage () {
		try {
			return SystemSettings.saveToStorage().await();
		} catch (final Throwable e) {
			L.e(e);
			return false;
		}
	}

}
