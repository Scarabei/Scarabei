package com.jfixby.cmns.jutils.desktop;

import java.util.Iterator;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Pool;
import com.jfixby.cmns.api.collections.PoolElementsSpawner;

public class DesktopPool<T> implements Pool<T> {

	final private List<T> legacy = JUtils.newList();

	private PoolElementsSpawner<T> spawner;

	public DesktopPool(PoolElementsSpawner<T> spawner) {
		this.spawner = spawner;
	}

	int in_air = 0;

	@Override
	public T obtain() {
		in_air++;
		if (legacy.size() == 0) {
			T instance = spawner.spawnNewInstance();
			return instance;
		} else {
			T last = legacy.removeLast();
			return last;
		}
	}

	@Override
	public void free(T instance) {
		in_air--;
		legacy.add(instance);
	}

	@Override
	public void freeAll(Collection<T> collection) {
		Iterator<T> iterator = collection.iterator();
		while (iterator.hasNext()) {
			T t = iterator.next();
			this.free(t);
		}
	}

}
