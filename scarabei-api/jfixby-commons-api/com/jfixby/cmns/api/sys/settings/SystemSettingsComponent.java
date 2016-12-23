
package com.jfixby.cmns.api.sys.settings;

import com.jfixby.cmns.api.assets.ID;
import com.jfixby.cmns.api.collections.Mapping;

public interface SystemSettingsComponent {

	void setExecutionMode (ExecutionMode execution_mode);

	void setFlag (String flag_name, boolean flag_value);

	boolean getFlag (String flag_name);

	String getStringParameter (String parameter_name);

	void setStringParameter (String parameter_name, String parameter_value);

	void setSystemAssetID (String parameter_name, ID parameter_value);

	ID getSystemAssetID (String parameter_name);

	void printSystemParameters ();

	boolean executionModeCovers (ExecutionMode mode);

	ExecutionMode getExecutionMode ();

	void setLongParameter (String parameterName, long parameterValue);

	long getLongParameter (String parameterName);

	Mapping<String, String> listAllSettings ();

}
