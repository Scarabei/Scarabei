
package com.jfixby.red.desktop.test;

import org.junit.Test;

import com.jfixby.cmns.api.assets.ID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.red.desktop.DesktopSetup;

public class MapRemoveTest {

	@Test
	public void test () {

		DesktopSetup.deploy();

		final Map<ID, List<Object>> map = Collections.newMap();
		final int N = 10;
		for (int i = 0; i < N; i++) {
			final ID key = Names.newAssetID(i + "");
			final List<Object> value = Collections.newList();
			map.put(key, value);
		}

		map.print("map");
		int k = -10;
		while (map.size() > 0) {
			k++;
			final ID key = Names.newAssetID(k + "");
			map.remove(key);
		}

		map.print("map2");

		assert (map.size() == 0);

	}

}
