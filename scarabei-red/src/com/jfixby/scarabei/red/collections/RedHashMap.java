
package com.jfixby.scarabei.red.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;

public class RedHashMap<K, V> implements Map<K, V> {
	final LinkedHashMap<K, V> hash_map = new LinkedHashMap<K, V>();
	final RedSet<K> keys_list = new RedSet<K>();

	@Override
	public void put (final K key, final V value) {
		this.hash_map.put(key, value);
		this.keys_list.add(key);
	}

	@Override
	public boolean containsKey (final Object key) {
		return this.hash_map.containsKey(key);

	}

	@Override
	public V get (final Object key) {
		return this.hash_map.get(key);
	}

	@Override
	public void clear () {
		this.hash_map.clear();
		this.keys_list.clear();
	}

	@Override
	public void print (final String tag) {
		L.d(tag, this);
	}

	// +----------------------------------------------------------------

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.hash_map == null) ? 0 : this.hash_map.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedHashMap<?, ?> other = (RedHashMap<?, ?>)obj;
		if (this.hash_map == null) {
			if (other.hash_map != null) {
				return false;
			}
		} else if (!this.hash_map.equals(other.hash_map)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString () {
		return this.hash_map.toString();
	}

	@Override
	public int size () {
		return this.hash_map.size();
	}

	@Override
	public void putAll (final Mapping<? extends K, ? extends V> other_map) {
		for (int i = 0; i < other_map.size(); i++) {
			final K key = other_map.getKeyAt(i);
			final V vlaue = other_map.getValueAt(i);
			this.put(key, vlaue);
		}
	}

	@Override
	public K getKeyAt (final long i) {
		return this.keys_list.getElementAt(i);
	}

	@Override
	public V getValueAt (final long i) {
		final K key = this.keys_list.getElementAt(i);
		return this.get(key);
	}

	@Override
	public V remove (final Object key) {
		this.keys_list.remove(key);
		return this.hash_map.remove(key);
	}

	@Override
	public Iterator<K> keysIterator () {
		return this.keys_list.iterator();
	}

	@Override
	public List<V> values () {
		final List<V> result = Collections.newList();
		for (int i = 0; i < this.size(); i++) {
			result.add(this.getValueAt(i));
		}
		return result;
	}

	@Override
	public Set<K> keys () {
		return this.keys_list;
	}

	@Override
	public void putJavaMap (final java.util.Map<? extends K, ? extends V> java_map) {
		final Iterator<? extends K> i = java_map.keySet().iterator();
		while (i.hasNext()) {
			final K key = i.next();
			final V value = java_map.get(key);
			this.put(key, value);
		}
	}

	@Override
	public java.util.Map<K, V> toJavaMap () {
		final LinkedHashMap<K, V> result = new LinkedHashMap<K, V>();
		result.putAll(this.hash_map);
		return result;
	}

	@Override
	public void removeAll (final Collection<?> keys) {
		this.keys_list.removeAll(keys);
		final Iterator<?> i = keys.iterator();
		while (i.hasNext()) {
			final Object key = i.next();
			this.hash_map.remove(key);
		}
	}

	@Override
	public void sortKeys () {
		this.keys_list.sort();
	}

	@Override
	public void sortKeys (final Comparator<? super K> keysComparator) {
		this.keys_list.sort(keysComparator);
	}

	@Override
	public EditableCollection<K> cutToSize (final int max_size) {
		if (max_size < 0) {
			Err.reportError("Negative target size: " + max_size);
		}

		if (max_size == 0) {
			this.clear();
		}

		final EditableCollection<K> to_remove = this.keys_list.splitAt(max_size);

		final Iterator<?> i = to_remove.iterator();
		while (i.hasNext()) {
			final Object key = i.next();
			this.hash_map.remove(key);
		}

		return to_remove;

	}
}
