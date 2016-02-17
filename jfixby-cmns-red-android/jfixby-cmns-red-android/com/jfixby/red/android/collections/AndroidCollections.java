package com.jfixby.red.android.collections;

import com.jfixby.cmns.api.collections.CollectionsComponent;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.red.collections.RedCollections;

public class AndroidCollections extends RedCollections implements CollectionsComponent {

	@Override
	public <T> List<T> newList() {
		return new AndroidList<T>();
	}

}
