
package com.jfixby.scarabei.red.ios.collections;

import com.jfixby.scarabei.api.collections.CollectionsComponent;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.red.collections.RedCollections;

public class iOSCollections extends RedCollections implements CollectionsComponent {

	@Override
	public <T> List<T> newList () {
		return new iOSList<T>();
	}

}
