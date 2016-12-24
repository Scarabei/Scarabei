
package com.jfixby.scarabei.red.name;

import java.util.Comparator;
import java.util.Iterator;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.NamespaceRegistry;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.collections.Set;

public class RedNamespaceRegistry<T> implements NamespaceRegistry<T> {

	final Map<ID, Set<T>> map = Collections.newMap();

	@Override
	public void put (ID object_name, Collection<T> value) {
		Set<T> collection = find(object_name);
		collection.addAll(value);
	}

	@Override
	public void clear () {
		map.clear();
	}

	@Override
	public Collection<T> remove (Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll (Mapping<? extends ID, ? extends Collection<T>> other_map) {
		for (ID key : other_map.keys()) {
			Collection<T> value = other_map.get(key);
			this.put(key, value);
		}

	}

	@Override
	public void putJavaMap (java.util.Map<? extends ID, ? extends Collection<T>> java_map) {
		for (ID key : java_map.keySet()) {
			Collection<T> value = java_map.get(key);
			this.put(key, value);
		}
	}

	@Override
	public void removeAll (Collection<?> keys) {
		map.removeAll(keys);
	}

	@Override
	public boolean containsKey (Object key) {
		return map.containsKey(key);
	}

	@Override
	public Collection<T> get (Object key) {
		Collection<T> result = map.get(key);

		if (result != null) {
			return result;
		}

		ID id = (ID)key;
		String last = id.getLastStep();
		if ("*".equals(last)) {
			id = id.parent();
			return wildcardGet(id);
		}

		return null;

	}

	private Collection<T> wildcardGet (ID id) {
		List<T> result = Collections.newList();
		for (int i = 0; i < this.map.size(); i++) {
			final ID k = this.map.keys().getElementAt(i);
			if (id.includes(k)) {
				Collection<T> vals = this.map.get(k);
				result.addAll(vals);
			}
		}
		// result.print("" + id);
		return result;
	}

	@Override
	public void print (String tag) {
		map.print(tag);
	}

	@Override
	public int size () {
		return map.size();
	}

	@Override
	public ID getKeyAt (long i) {
		return map.getKeyAt(i);
	}

	@Override
	public Collection<T> getValueAt (long i) {
		return map.getValueAt(i);
	}

	@Override
	public Iterator<ID> keysIterator () {
		return map.keysIterator();
	}

	@Override
	public Collection<Collection<T>> values () {
		return Collections.castCollection(map.values());
	}

	@Override
	public Collection<ID> keys () {
		return map.keys();
	}

	@Override
	public java.util.Map<ID, Collection<T>> toJavaMap () {
		Map<ID, Collection<T>> map_t = Collections.castMap(map);
		return map_t.toJavaMap();
	}

	@Override
	public void put (ID object_name, T object) {
		Set<T> collection = find(object_name);
		collection.add(object);

	}

	private Set<T> find (ID object_name) {
		Set<T> collection = map.get(object_name);
		if (collection == null) {
			collection = Collections.newSet();
			map.put(object_name, collection);
		}
		return collection;
	}

	@Override
	public Collection<T> allValues () {
		List<T> result = Collections.newList();
		for (int i = 0; i < this.map.size(); i++) {
			Set<T> values = this.map.getValueAt(i);
			result.addAll(values);
		}
		return result;
	}

	@Override
	public void sortKeys () {
		map.sortKeys();
	}

	@Override
	public void sortKeys (Comparator<? super ID> keysComparator) {
		map.sortKeys(keysComparator);
	}

	@Override
	public EditableCollection<ID> cutToSize (int max_size) {
		return this.map.cutToSize(max_size);
	}

}
