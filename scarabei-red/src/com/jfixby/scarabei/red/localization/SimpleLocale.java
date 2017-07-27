
package com.jfixby.scarabei.red.localization;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.localize.Locale;
import com.jfixby.scarabei.api.localize.StringValueID;

public class SimpleLocale implements Locale {

	public String getLanguage () {
		return this.language;
	}

	public SimpleLocale () {
	}

	public int size () {
		return this.mapping.size();
	}

	private String language;
	private final Map<String, String> mapping = Collections.newMap();

	public SimpleLocale (final SimpleLocalizationSpecs loc_specs) {
		this.language = loc_specs.getLanguageName();
	}

	public void set (final String parameter_name, final String parameter_localized_value) {
		this.mapping.put(parameter_name, parameter_localized_value);
	}

	@Override
	public String resolveString (final String parameter_name) {
		final String value = this.mapping.get(parameter_name);
		if (value != null) {
			return value;
		} else {
			Err.reportError("Unable to translate: " + parameter_name);
		}
		return value;
	}

	public void set (final StringValueID parameter_name, final String parameter_localized_value) {
		this.set(parameter_name.toString(), parameter_localized_value);
	}

	@Override
	public String resolveString (final StringValueID parameter_name) {
		return this.resolveString(parameter_name.toString());
	}

	public SimpleLocaleEntry getEntry (final int i) {
		final String key = this.mapping.getKeyAt(i);
		final String value = this.mapping.getValueAt(i);
		return new SimpleLocaleEntry(key, value);
	}

}
