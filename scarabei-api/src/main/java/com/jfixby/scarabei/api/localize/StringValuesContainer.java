
package com.jfixby.scarabei.api.localize;

public interface StringValuesContainer {

	StringValueID getIdByValue (String ui_string_value);

	StringValueLocalizations getLocalizationsFor (StringValueID string_value_id);

	StringValueLocalizationSpecs newLocalizationSpecs ();

	StringValueID spawnNewStringValueID (String string_value_id_string);

	LocalizedStringValue newLocalizationEntry (StringValueLocalizationSpecs specs);

	LocalizedStringValue getLocalizationFor (String locale_name, StringValueID string_value_id);

	LocalizedStringValuesList listAllValues ();

}
