package com.jfixby.red.localization;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.localize.Locale;
import com.jfixby.cmns.api.localize.StringValueID;

public class SimpleLocale implements Locale {

	public String getLanguage() {
		return language;
	}

	public SimpleLocale() {
	}

	public int size() {
		return mapping.size();
	}

	private String language;
	private Map<String, String> mapping = JUtils.newMap();

	public SimpleLocale(SimpleLocalizationSpecs loc_specs) {
		language = loc_specs.getLanguageName();
	}

	public void set(String parameter_name, String parameter_localized_value) {
		mapping.put(parameter_name, parameter_localized_value);
	}

	@Override
	public String resolveString(String parameter_name) {
		String value = mapping.get(parameter_name);
		if (value != null) {
			return value;
		} else {
			throw new Error("Unable to translate: " + parameter_name);
		}
	}

	public void set(StringValueID parameter_name,
			String parameter_localized_value) {
		this.set(parameter_name.toString(), parameter_localized_value);
	}

	@Override
	public String resolveString(StringValueID parameter_name) {
		return this.resolveString(parameter_name.toString());
	}

	public SimpleLocaleEntry getEntry(int i) {
		String key = mapping.getKeyAt(i);
		String value = mapping.getValueAt(i);
		return new SimpleLocaleEntry(key, value);
	}

}
