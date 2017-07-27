
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettingsComponent;

public class RedSystemSettings implements SystemSettingsComponent {
	private ExecutionMode execution_mode = ExecutionMode.EARLY_DEVELOPMENT;

	final Map<String, Boolean> flags = Collections.newMap();
	final Map<String, Long> longs = Collections.newMap();
	final Map<String, String> strings = Collections.newMap();
	final Map<String, ID> assets = Collections.newMap();

	@Override
	public void printSystemParameters () {
		L.d("---[SystemSettings]-----------------------------------");
		L.d("ExecutionMode", this.execution_mode);
		this.flags.print("   Flags  ");
		this.longs.print("   Longs  ");
		this.strings.print("   Strings");
		this.assets.print("   Assets ");
		L.d("---[SystemSettings-END]-----------------------------------");
	}

	@Override
	public Mapping<String, String> listAllSettings () {
		final Map<String, String> params = Collections.newMap();
		params.put("ExecutionMode", "" + this.execution_mode);
		collect("flag", params, this.flags);
		collect("long", params, this.longs);
		collect("string", params, this.strings);
		collect("assets", params, this.assets);
		return params;
	}

	static private void collect (final String string, final Map<String, String> params, final Map<String, ?> input) {
		for (final String flagName : input.keys()) {
			final String key = string + "." + flagName;
			final String value = "" + input.get(flagName);
			params.put(key, value);
		}
	}

	@Override
	public void setExecutionMode (final ExecutionMode executionMode) {
		Debug.checkNull("ExecutionMode", executionMode);
		this.execution_mode = executionMode;
	}

	@Override
	public void setFlag (final String flag_name, final boolean flag_value) {
		this.flags.put(flag_name, flag_value);
	}

	@Override
	public boolean getFlag (final String flag_name) {
		final Boolean value = this.flags.get(flag_name);
		if (value == null) {
			L.d("Flag not found", flag_name);
			return false;
		}
		return value;
	}

	@Override
	public String getStringParameter (final String parameter_name) {
		final String value = this.strings.get(parameter_name);
		if (value == null) {
			L.d("Parameter not found", parameter_name);
			return null;
		}
		return value;
	}

	@Override
	public void setStringParameter (final String parameter_name, final String parameter_value) {
		this.strings.put(parameter_name, parameter_value);
	}

	@Override
	public void setSystemAssetID (final String parameter_name, final ID parameter_value) {
		this.assets.put(parameter_name, parameter_value);
	}

	@Override
	public ID getSystemAssetID (final String parameter_name) {
		final ID value = this.assets.get(parameter_name);
		if (value == null) {
			L.d("Parameter not found", parameter_name);
			return null;
		}
		return value;
	}

	@Override
	public boolean executionModeCovers (final ExecutionMode execution_mode) {
		return this.execution_mode.covers(execution_mode);
	}

	@Override
	public ExecutionMode getExecutionMode () {
		return this.execution_mode;
	}

	@Override
	public void setLongParameter (final String parameterName, final long parameterValue) {
		this.longs.put(parameterName, parameterValue);
	}

	@Override
	public long getLongParameter (final String parameterName) {
		final Long value = this.longs.get(parameterName);
		if (value == null) {
			L.d("Parameter not found", parameterName);
			return 0;
		}
		return value;
	}

}
