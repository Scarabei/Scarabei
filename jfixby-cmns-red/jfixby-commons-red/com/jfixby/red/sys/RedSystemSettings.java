package com.jfixby.red.sys;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.settings.ExecutionMode;
import com.jfixby.cmns.api.sys.settings.SystemSettingsComponent;

public class RedSystemSettings implements SystemSettingsComponent {

    final Map<String, Boolean> flags = Collections.newMap();
    final Map<String, String> strings = Collections.newMap();
    final Map<String, AssetID> assets = Collections.newMap();

    public void printSystemParameters() {
	L.d("---[SystemSettings]-----------------------------------");
	flags.print("   Flags  ");
	strings.print("   Strings");
	assets.print("   Assets ");
	L.d("---[SystemSettings-END]-----------------------------------");
    }

    private ExecutionMode execution_mode = ExecutionMode.EARLY_DEVELOPMENT;

    public void setExecutionMode(final ExecutionMode executionMode) {
	Debug.checkNull("ExecutionMode", executionMode);
	this.execution_mode = executionMode;
    }

    public void setFlag(String flag_name, boolean flag_value) {
	this.flags.put(flag_name, flag_value);
    }

    public boolean getFlag(String flag_name) {
	Boolean value = flags.get(flag_name);
	if (value == null) {
	    L.d("Flag not found", flag_name);
	    return false;
	}
	return value;
    }

    public String getStringParameter(String parameter_name) {
	String value = strings.get(parameter_name);
	if (value == null) {
	    L.d("Parameter not found", parameter_name);
	    return null;
	}
	return value;
    }

    public void setStringParameter(String parameter_name, String parameter_value) {
	strings.put(parameter_name, parameter_value);
    }

    public void setSystemAssetID(String parameter_name, AssetID parameter_value) {
	assets.put(parameter_name, parameter_value);
    }

    public AssetID getSystemAssetID(String parameter_name) {
	AssetID value = assets.get(parameter_name);
	if (value == null) {
	    L.d("Parameter not found", parameter_name);
	    return Names.newAssetID("com.jfixby.redtriplane.fokker.render.raster_is_missing");
	}
	return value;
    }

    public boolean executionModeIs(final ExecutionMode execution_mode) {
	return execution_mode == this.execution_mode;
    }

    public ExecutionMode getExecutionMode() {
	return this.execution_mode;
    }

}
