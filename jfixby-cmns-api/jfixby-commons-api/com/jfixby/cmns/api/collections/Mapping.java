package com.jfixby.cmns.api.collections;

import java.util.Iterator;

public interface Mapping<K, V> {

	boolean containsKey(Object key);

	V get(Object key);

	void print(String tag);

	int size();

	K getKeyAt(int i);

	V getValueAt(int i);

	Iterator<K> keysIterator();

	Collection<V> values();

	Collection<K> keys();
	
	java.util.Map<K, V> toJavaMap();

}
