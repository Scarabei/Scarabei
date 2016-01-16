package com.jfixby.red.lambda;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.lambda.λFunctionCache;

public class ArrayCache<Q, T> implements λFunctionCache<Collection<Q>, T> {
	private float max_array_size;

	public ArrayCache(float max_array_size) {
		this.max_array_size = max_array_size;
	}

	public ArrayCache() {
		this(Float.MAX_VALUE);
	}

	final Map<List<Q>, T> mapping = Collections.newMap();

	@Override
	public T get(Collection<Q> key) {
		if (max_array_size < key.size()) {
			return null;
		}
		List<Q> input = toKey(key);
		T cached = mapping.get(input);
		if (cached != null) {
			return cached;
		}
		return null;
	}

	private List<Q> toKey(Collection<Q> key) {
		return Collections.newList(key);
	}

	@Override
	public void put(Collection<Q> key, T value) {
		if (max_array_size < key.size()) {
			return;
		}
		List<Q> input = toKey(key);
		mapping.put(input, value);
	}

	@Override
	public void print(String tag) {
		mapping.print(tag);
	}

}
