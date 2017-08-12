
package com.jfixby.scarabei.api.sys.settings;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.promise.Promise;

public interface SystemSettingsComponent {

	void setExecutionMode (ExecutionMode execution_mode);

	void setFlag (String flag_name, boolean flag_value);

	boolean getFlag (String flag_name);

	String getStringParameter (String parameter_name, final String defaultValue);

	void setStringParameter (String parameter_name, String parameter_value);

	void setSystemAssetID (String parameter_name, ID parameter_value);

	ID getSystemAssetID (String parameter_name);

	boolean executionModeIsAtLeast (ExecutionMode mode);

	ExecutionMode getExecutionMode ();

	void setIntParameter (String parameterName, long parameterValue);

	long getIntParameter (String parameterName);

	Map<String, Object> listAllSettings ();

	void clearAll ();

	public Promise<Boolean> saveToStorage ();

}
