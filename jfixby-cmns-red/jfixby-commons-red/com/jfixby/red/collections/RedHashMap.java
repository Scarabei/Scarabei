package com.jfixby.red.collections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.collections.Mapping;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.log.L;

public class RedHashMap<K, V> implements Map<K, V> {
    final HashMap<K, V> hash_map = new HashMap<K, V>();
    final RedSet<K> keys_list = new RedSet<K>();

    @Override
    public void put(K key, V value) {
	hash_map.put(key, value);
	keys_list.add(key);
    }

    @Override
    public boolean containsKey(Object key) {
	return this.hash_map.containsKey(key);

    }

    @Override
    public V get(Object key) {
	return this.hash_map.get(key);
    }

    @Override
    public void clear() {
	this.hash_map.clear();
	this.keys_list.clear();
    }

    @Override
    public void print(String tag) {
	L.d(tag, this);
    }

    // +----------------------------------------------------------------

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((hash_map == null) ? 0 : hash_map.hashCode());
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
	RedHashMap<?, ?> other = (RedHashMap<?, ?>) obj;
	if (hash_map == null) {
	    if (other.hash_map != null)
		return false;
	} else if (!hash_map.equals(other.hash_map))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return hash_map.toString();
    }

    @Override
    public int size() {
	return this.hash_map.size();
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
    public K getKeyAt(long i) {
	return this.keys_list.getElementAt(i);
    }

    @Override
    public V getValueAt(long i) {
	K key = keys_list.getElementAt(i);
	return this.get(key);
    }

    @Override
    public V remove(Object key) {
	this.keys_list.remove(key);
	return this.hash_map.remove(key);
    }

    @Override
    public Iterator<K> keysIterator() {
	return this.keys_list.iterator();
    }

    @Override
    public List<V> values() {
	return Collections.newList(this.hash_map.values());
    }

    @Override
    public Set<K> keys() {
	return this.keys_list;
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
    public java.util.Map<K, V> toJavaMap() {
	HashMap<K, V> result = new HashMap<K, V>();
	result.putAll(this.hash_map);
	return result;
    }

    @Override
    public void removeAll(Collection<?> keys) {
	this.keys_list.removeAll(keys);
	Iterator<?> i = keys.iterator();
	while (i.hasNext()) {
	    Object key = i.next();
	    this.hash_map.remove(key);
	}
    }

    @Override
    public void sortKeys() {
	this.keys_list.sort();
    }

    @Override
    public void sortKeys(Comparator<? super K> keysComparator) {
	this.keys_list.sort(keysComparator);
    }

    @Override
    public EditableCollection<K> cutToSize(int max_size) {
	if (max_size < 0) {
	    Err.reportError("Negative target size: " + max_size);
	}

	if (max_size == 0) {
	    this.clear();
	}

	final EditableCollection<K> to_remove = this.keys_list.splitAt(max_size);

	final Iterator<?> i = to_remove.iterator();
	while (i.hasNext()) {
	    Object key = i.next();
	    this.hash_map.remove(key);
	}

	return to_remove;

    }
}