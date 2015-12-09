package com.jfixby.cmns.jutils.desktop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.collections.Mapping;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.log.L;

public class DesktopBadMap<K, V> implements Map<K, V> {

	static class Pair<K, V> {
		Pair(K k, V v) {
			this.key = k;
			this.value = v;
		}

		final K key;
		final V value;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			return true;
		}

	}

	final LinkedList<K> keys_list = new LinkedList<K>();
	final LinkedList<Pair<K, V>> pairs_list = new LinkedList<Pair<K, V>>();

	@Override
	public void put(K key, V value) {
		Pair<K, V> pair = new Pair<K, V>(key, value);
		if (keys_list.remove(key)) {
			pairs_list.remove(pair);
		}
		checkSize();
		keys_list.add(key);
		pairs_list.add(pair);
		checkSize();
	}

	private void checkSize() {
		if (keys_list.size() != this.pairs_list.size()) {
			L.d("keys_list", keys_list);
			L.d(" pairs_list", pairs_list);
			throw new Error();
		}
	}

	@Override
	public boolean containsKey(Object key) {
		return this.keys_list.contains(key);
	}

	@Override
	public V get(Object key) {
		if (!keys_list.contains(key)) {
			return null;
		}
		return findPairByKey(key).value;
	}

	private Pair<K, V> findPairByKey(Object key) {
		final boolean not_null_search = key != null;
		for (Iterator<Pair<K, V>> i = this.pairs_list.iterator(); i.hasNext();) {
			Pair<K, V> e = i.next();
			if (e.key == key) {
				return e;
			}
			if (not_null_search) {
				if (key.equals(e.key)) {
					return e;
				}
			}
		}

		Err.reportError("Map is corrupted");
		throw new Error();
	}

	@Override
	public void clear() {
		this.pairs_list.clear();
		this.keys_list.clear();
	}

	@Override
	public void print(String tag) {
		L.d(tag, this);
	}

	// +----------------------------------------------------------------

	@Override
	public int size() {
		this.checkSize();
		return this.keys_list.size();
	}

	@Override
	public void putAll(Mapping<? extends K, ? extends V> other_map) {
		for (int i = 0; i < other_map.size(); i++) {
			K key = other_map.getKeyAt(i);
			V vlaue = other_map.getValueAt(i);
			this.put(key, vlaue);
		}
	}

	@Override
	public K getKeyAt(int i) {
		return this.keys_list.get(i);
	}

	@Override
	public V getValueAt(int i) {
		K key = keys_list.get(i);
		return this.get(key);
	}

	@Override
	public V remove(Object key) {
		this.keys_list.remove(key);
		Pair<K, V> to_remove = this.findPairByKey(key);
		this.pairs_list.remove(to_remove);
		this.checkSize();
		return to_remove.value;
	}

	@Override
	public Iterator<K> keysIterator() {
		return this.keys_list.iterator();
	}

	@Override
	public Collection<V> values() {
		Err.reportWarning("Memory leak");
		return JUtils.newList(this.toJavaMap().values());
	}

	@Override
	public Set<K> keys() {
		return JUtils.newSet(this.keys_list);
	}

	@Override
	public void putJavaMap(java.util.Map<? extends K, ? extends V> java_map) {
		Iterator<? extends K> i = java_map.keySet().iterator();
		while (i.hasNext()) {
			K key = i.next();
			V value = java_map.get(key);
			put(key, value);
		}
	}

	@Override
	public void removeAll(Collection<?> keys) {
		Iterator<?> i = keys.iterator();
		while (i.hasNext()) {
			Object key = i.next();
			this.remove(key);
		}
	}

	@Override
	public java.util.Map<K, V> toJavaMap() {
		HashMap<K, V> result = new HashMap<K, V>();
		for (Iterator<Pair<K, V>> i = this.pairs_list.iterator(); i.hasNext();) {
			Pair<K, V> e = i.next();
			result.put(e.key, e.value);
		}
		return result;
	}
}
