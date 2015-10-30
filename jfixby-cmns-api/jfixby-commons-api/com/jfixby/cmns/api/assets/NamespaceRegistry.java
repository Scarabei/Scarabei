package com.jfixby.cmns.api.assets;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Map;

public interface NamespaceRegistry<T> extends Map<AssetID, Collection<T>> {

	void put(AssetID object_name, T object);

	Collection<T> allValues();

}
