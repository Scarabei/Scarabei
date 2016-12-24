
package com.jfixby.scarabei.red.android.collections;

import com.jfixby.scarabei.api.collections.CollectionsComponent;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.red.collections.RedCollections;

public class AndroidCollections extends RedCollections implements CollectionsComponent {

	@Override
	public <T> List<T> newList () {
		return new AndroidList<T>();
	}

}
