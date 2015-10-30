package com.jfixby.red.localization;

import java.io.IOException;
import java.util.HashMap;

import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.localize.Locale;
import com.jfixby.cmns.api.localize.LocalizationComponent;
import com.jfixby.cmns.api.localize.LocalizedStringValue;
import com.jfixby.cmns.api.localize.LocalizedStringValuesList;
import com.jfixby.cmns.api.localize.StringValueID;
import com.jfixby.cmns.api.localize.StringValueLocalizationSpecs;
import com.jfixby.cmns.api.localize.StringValueLocalizations;
import com.jfixby.cmns.api.localize.StringValuesContainer;

public class SimpleLocalization implements LocalizationComponent,
		StringValuesContainer {

	@Override
	public StringValuesContainer getStringValuesContainer() {
		return this;
	}

	public SimpleLocalizationSpecs newSimpleLocalizationSpecs() {
		return new SimpleLocalizationSpecs();
	}

	public SimpleLocale newLocalization(SimpleLocalizationSpecs loc_specs) {
		return new SimpleLocale(loc_specs);
	}

	public void writeToFile(File file, Locale locale) throws IOException {
		String serialized_locale = Json.serializeToString(locale);
		file.writeString(serialized_locale);
	}

	public SimpleLocale readFromFile(File file) throws IOException {

		String data = file.readToString();

		SimpleLocale locale = Json.deserializeFromString(SimpleLocale.class,
				data);

		return locale;
	}

	@Override
	public Locale getLocale(String locale_name) {
		throw new Error();

	}

	@Override
	public StringValueID getIdByValue(String ui_string_value) {
		throw new Error();
	}

	@Override
	public StringValueLocalizations getLocalizationsFor(
			StringValueID string_value_id) {
		throw new Error();
	}

	@Override
	public StringValueLocalizationSpecs newLocalizationSpecs() {
		throw new Error();
	}

	@Override
	public StringValueID spawnNewStringValueID(String string_value_id_string) {
		return new RedStringValueID(string_value_id_string);
	}

	@Override
	public LocalizedStringValue newLocalizationEntry(
			StringValueLocalizationSpecs specs) {
		throw new Error();
	}

	@Override
	public LocalizedStringValue getLocalizationFor(String locale_name,
			StringValueID string_value_id) {
		throw new Error();
	}

	@Override
	public LocalizedStringValuesList listAllValues() {
		throw new Error();
	}

}
