
package com.jfixby.scarabei.api.strings;

import com.jfixby.scarabei.api.collections.Collection;

public interface Text extends RawStringProvider {

	public String getLocaleName ();

	public boolean hasLocalization (String locale_name);

	Collection<String> listLocales ();

	boolean switchLocale (String locale_name);

}
