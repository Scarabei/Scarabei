
package com.jfixby.scarabei.api.assets;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Map;

public interface NamespaceRegistry<T> extends Map<ID, Collection<T>> {

	void put (ID object_name, T object);

	Collection<T> allValues ();

}
