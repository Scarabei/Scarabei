
package com.jfixby.red.localization;

import java.io.IOException;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.localize.Locale;
import com.jfixby.cmns.api.localize.LocalizationComponent;
import com.jfixby.cmns.api.localize.LocalizedStringValue;
import com.jfixby.cmns.api.localize.LocalizedStringValuesList;
import com.jfixby.cmns.api.localize.StringValueID;
import com.jfixby.cmns.api.localize.StringValueLocalizationSpecs;
import com.jfixby.cmns.api.localize.StringValueLocalizations;
import com.jfixby.cmns.api.localize.StringValuesContainer;

public class SimpleLocalization implements LocalizationComponent, StringValuesContainer {

	@Override
	public StringValuesContainer getStringValuesContainer () {
		return this;
	}

	public SimpleLocalizationSpecs newSimpleLocalizationSpecs () {
		return new SimpleLocalizationSpecs();
	}

	public SimpleLocale newLocalization (final SimpleLocalizationSpecs loc_specs) {
		return new SimpleLocale(loc_specs);
	}

	public void writeToFile (final File file, final Locale locale) throws IOException {
		final String serialized_locale = Json.serializeToString(locale).toString();
		file.writeString(serialized_locale);
	}

	public SimpleLocale readFromFile (final File file) throws IOException {

		final String data = file.readToString();

		// SimpleLocale locale = Json.deserializeFromString(SimpleLocale.class,
		// data);
		Err.reportError("Not implemented!");
		return null;
	}

	@Override
	public Locale getLocale (final String locale_name) {
		Err.reportNotImplementedYet();
		return null;

	}

	@Override
	public StringValueID getIdByValue (final String ui_string_value) {
		Err.reportNotImplementedYet();
		return null;
	}

	@Override
	public StringValueLocalizations getLocalizationsFor (final StringValueID string_value_id) {
		Err.reportNotImplementedYet();
		return null;
	}

	@Override
	public StringValueLocalizationSpecs newLocalizationSpecs () {
		Err.reportNotImplementedYet();
		return null;
	}

	@Override
	public StringValueID spawnNewStringValueID (final String string_value_id_string) {
		return new RedStringValueID(string_value_id_string);
	}

	@Override
	public LocalizedStringValue newLocalizationEntry (final StringValueLocalizationSpecs specs) {
		Err.reportNotImplementedYet();
		return null;
	}

	@Override
	public LocalizedStringValue getLocalizationFor (final String locale_name, final StringValueID string_value_id) {
		Err.reportNotImplementedYet();
		return null;
	}

	@Override
	public LocalizedStringValuesList listAllValues () {
		Err.reportNotImplementedYet();
		return null;
	}

}
