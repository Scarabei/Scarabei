
package com.jfixby.scarabei.red.desktop.sys.settings;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettingsComponent;

public class DesktopSystemSettings implements SystemSettingsComponent {
	private ExecutionMode execution_mode = ExecutionMode.EARLY_DEVELOPMENT;

	final Map<ID, Boolean> flags = Collections.newMap();
	final Map<ID, Long> longs = Collections.newMap();
	final Map<ID, String> strings = Collections.newMap();
	final Map<ID, ID> assets = Collections.newMap();

	public DesktopSystemSettings () {
		final java.util.Map<String, String> list = System.getenv();
		for (final String key : list.keySet()) {
			if (!Names.isValidString(key)) {
				continue;
			}
			final ID id = Names.newID(key);
			this.strings.put(id, list.get(key));
		}
	}

	@Override
	public Map<ID, Object> listAllSettings () {
		final Map<ID, Object> params = Collections.newMap();
// Err.throwNotImplementedYet();

// params.put("ExecutionMode", "" + this.execution_mode);
		collect("flag", params, this.flags);
		collect("long", params, this.longs);
		collect("string", params, this.strings);
		collect("assets", params, this.assets);
		return params;
	}

	static private void collect (final String string, final Map<ID, Object> params, final Map<ID, ?> input) {
		params.putAll(input);
	}

	@Override
	public void setExecutionMode (final ExecutionMode executionMode) {
		Debug.checkNull("ExecutionMode", executionMode);
		this.execution_mode = executionMode;
	}

	@Override
	public void setFlag (final ID flag_name, final boolean flag_value) {
		this.flags.put(flag_name, flag_value);
	}

	@Override
	public boolean getFlag (final ID flag_name) {
		final Boolean value = this.flags.get(flag_name);
		if (value == null) {
			L.d("Flag not found", flag_name);
			return false;
		}
		return value;
	}

	@Override
	public String getStringParameter (final ID parameter_name, final String defaultValue) {
		final String value = this.strings.get(parameter_name);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	@Override
	public void setStringParameter (final ID parameter_name, final String parameter_value) {
		this.strings.put(parameter_name, parameter_value);
	}

	@Override
	public void setSystemAssetID (final ID parameter_name, final ID parameter_value) {
		this.assets.put(parameter_name, parameter_value);
	}

	@Override
	public ID getSystemAssetID (final ID parameter_name) {
		final ID value = this.assets.get(parameter_name);
		if (value == null) {
			L.d("Parameter not found", parameter_name);
			return null;
		}
		return value;
	}

	@Override
	public boolean executionModeIsAtLeast (final ExecutionMode execution_mode) {
		return this.execution_mode.isAtLeast(execution_mode);
	}

	@Override
	public ExecutionMode getExecutionMode () {
		return this.execution_mode;
	}

	@Override
	public void setIntParameter (final ID parameterName, final long parameterValue) {
		this.longs.put(parameterName, parameterValue);
	}

	@Override
	public long getIntParameter (final ID parameterName) {
		final Long value = this.longs.get(parameterName);
		if (value == null) {
			L.d("Parameter not found", parameterName);
			return 0;
		}
		return value;
	}

	@Override
	public void clearAll () {
		this.flags.clear();
		this.longs.clear();
		this.strings.clear();
		this.assets.clear();
	}

	@Override
	public boolean saveToStorage () {
		Err.throwNotImplementedYet();
		return false;
	}

	@Override
	public String getRequiredStringParameter (final ID name) {
		final String value = this.getStringParameter(name, null);
		Debug.checkNull(name.toString(), value);
		return value;
	}

}
