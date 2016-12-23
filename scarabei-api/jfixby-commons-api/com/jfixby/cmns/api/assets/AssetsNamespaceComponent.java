
package com.jfixby.cmns.api.assets;

import com.jfixby.cmns.api.collections.Collection;

public interface AssetsNamespaceComponent {

	ID newID (String asset_id_string);

	String SEPARATOR ();

	<T> NamespaceRegistry<T> newRegistry ();

	ID ROOT ();

	ID newID (Collection<String> list);

}
