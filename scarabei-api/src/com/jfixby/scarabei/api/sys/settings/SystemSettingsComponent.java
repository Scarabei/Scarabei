
package com.jfixby.scarabei.api.sys.settings;

import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.names.ID;

public interface SystemSettingsComponent {

	void setExecutionMode (ExecutionMode execution_mode);

	void setFlag (ID flag_name, boolean flag_value);

	boolean getFlag (ID flag_name);

	String getStringParameter (ID parameter_name, final String defaultValue);

	void setStringParameter (ID parameter_name, String parameter_value);

	void setSystemAssetID (ID parameter_name, ID parameter_value);

	ID getSystemAssetID (ID parameter_name);

	boolean executionModeIsAtLeast (ExecutionMode mode);

	ExecutionMode getExecutionMode ();

	void setIntParameter (ID parameterName, long parameterValue);

	long getIntParameter (ID parameterName);

	Map<ID, Object> listAllSettings ();

	void clearAll ();

	public boolean saveToStorage ();

}
