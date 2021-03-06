
package com.jfixby.scarabei.api.names;

import com.jfixby.scarabei.api.collections.Collection;

public interface AssetsNamespaceComponent {

	ID newID (String asset_id_string);

	String SEPARATOR ();

	<T> NamespaceRegistry<T> newRegistry ();

	ID ROOT ();

	ID newID (Collection<String> list);

	boolean isValidString (String value);

}
