package com.jfixby.red.gwt.collections;

import com.jfixby.cmns.api.collections.CollectionsComponent;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.red.collections.RedCollections;

public class GwtCollections extends RedCollections implements CollectionsComponent {

	@Override
	public <T> List<T> newList() {
		return new GwtList<T>();
	}

}
