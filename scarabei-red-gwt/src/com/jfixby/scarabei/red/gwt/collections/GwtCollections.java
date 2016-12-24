package com.jfixby.scarabei.red.gwt.collections;

import com.jfixby.scarabei.api.collections.CollectionsComponent;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.red.collections.RedCollections;

public class GwtCollections extends RedCollections implements CollectionsComponent {

	@Override
	public <T> List<T> newList() {
		return new GwtList<T>();
	}

}
