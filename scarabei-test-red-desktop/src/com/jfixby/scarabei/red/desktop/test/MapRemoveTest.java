
package com.jfixby.scarabei.red.desktop.test;

import org.junit.Test;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;

public class MapRemoveTest {

	@Test
	public void test () {

		ScarabeiDesktop.deploy();

		final Map<ID, List<Object>> map = Collections.newMap();
		final int N = 10;
		for (int i = 0; i < N; i++) {
			final ID key = Names.newID(i + "");
			final List<Object> value = Collections.newList();
			map.put(key, value);
		}

		map.print("map");
		int k = -10;
		while (map.size() > 0) {
			k++;
			final ID key = Names.newID(k + "");
			map.remove(key);
		}

		map.print("map2");

		assert (map.size() == 0);

	}

}
