package com.jfixby.cmns.api.collections;

public interface Map<K, V> extends Mapping<K, V> {

	void put(K key, V value);

	void clear();

	V remove(Object key);

	void putAll(Mapping<? extends K, ? extends V> other_map);

	void putJavaMap(java.util.Map<? extends K, ? extends V> java_map);

	void removeAll(Collection<?> keys);

}
