package com.jfixby.red.desktop.sys;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.ExecutionMode;
import com.jfixby.red.sys.RedSystem;

public class DesktopSystem extends RedSystem {

	@Override
	public void exit() {
		L.d("EXIT");
		System.exit(0);
	}

	@Override
	public boolean sleep(long period) {
		try {
			Thread.sleep(period);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	final Map<String, Boolean> flags = JUtils.newMap();
	final Map<String, String> strings = JUtils.newMap();
	final Map<String, AssetID> assets = JUtils.newMap();

	private ExecutionMode execution_mode;

	@Override
	public void setExecutionMode(ExecutionMode execution_mode) {
		// L.d("ExecutionMode", execution_mode);
		this.execution_mode = execution_mode;
	}

	@Override
	public void setFlag(String flag_name, boolean flag_value) {
		flags.put(flag_name, flag_value);
	}

	@Override
	public boolean getFlag(String flag_name) {
		Boolean value = flags.get(flag_name);
		if (value == null) {
			L.d("Flag not found", flag_name);
			return false;
		}
		return value;
	}

	@Override
	public String getStringParameter(String parameter_name) {
		String value = strings.get(parameter_name);
		if (value == null) {
			L.d("Parameter not found", parameter_name);
			return null;
		}
		return value;
	}

	@Override
	public void setStringParameter(String parameter_name, String parameter_value) {
		strings.put(parameter_name, parameter_value);
	}

	@Override
	public void setSystemAssetID(String parameter_name, AssetID parameter_value) {
		assets.put(parameter_name, parameter_value);
	}

	@Override
	public AssetID getSystemAssetID(String parameter_name) {
		AssetID value = assets.get(parameter_name);
		if (value == null) {
			L.d("Parameter not found", parameter_name);
			return Names.newAssetID("com.jfixby.redtriplane.fokker.render.raster_is_missing");
		}
		return value;
	}

}
