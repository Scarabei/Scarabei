
package com.jfixby.scarabei.red.localization;

public class SimpleLocaleEntry {

	private String parameter_name;
	private String parameter_localized_value;

	public SimpleLocaleEntry (String parameter_name, String parameter_localized_value) {
		this.parameter_name = parameter_name;
		this.parameter_localized_value = parameter_localized_value;
	}

	public String getValueID () {
		return parameter_name;
	}

	public String getLocalizedValue () {
		return parameter_localized_value;
	}

}
