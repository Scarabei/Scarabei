
package com.jfixby.scarabei.api.localize;

public interface StringValueLocalizationSpecs {

	void setLocaleName (String locale_name);

	void setStringValueId (StringValueID value_id);

	void setLocalizedValue (String localizedValue);

	String getLocaleName ();

	StringValueID getStringValueId ();

	String getLocalizedValue ();

}
