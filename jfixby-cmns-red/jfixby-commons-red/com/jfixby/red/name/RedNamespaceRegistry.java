
package com.jfixby.red.name;

import java.util.Comparator;
import java.util.Iterator;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.NamespaceRegistry;
import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.collections.Mapping;
import com.jfixby.cmns.api.collections.Set;

public class RedNamespaceRegistry<T> implements NamespaceRegistry<T> {

	final Map<AssetID, Set<T>> map = Collections.newMap();

	@Override
	public void put (AssetID object_name, Collection<T> value) {
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
	public void putAll (Mapping<? extends AssetID, ? extends Collection<T>> other_map) {
		for (AssetID key : other_map.keys()) {
			Collection<T> value = other_map.get(key);
			this.put(key, value);
		}

	}

	@Override
	public void putJavaMap (java.util.Map<? extends AssetID, ? extends Collection<T>> java_map) {
		for (AssetID key : java_map.keySet()) {
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

		AssetID id = (AssetID)key;
		String last = id.getLastStep();
		if ("*".equals(last)) {
			id = id.parent();
			return wildcardGet(id);
		}

		return null;

	}

	private Collection<T> wildcardGet (AssetID id) {
		List<T> result = Collections.newList();
		for (int i = 0; i < this.map.size(); i++) {
			final AssetID k = this.map.keys().getElementAt(i);
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
	public AssetID getKeyAt (long i) {
		return map.getKeyAt(i);
	}

	@Override
	public Collection<T> getValueAt (long i) {
		return map.getValueAt(i);
	}

	@Override
	public Iterator<AssetID> keysIterator () {
		return map.keysIterator();
	}

	@Override
	public Collection<Collection<T>> values () {
		return Collections.castCollection(map.values());
	}

	@Override
	public Collection<AssetID> keys () {
		return map.keys();
	}

	@Override
	public java.util.Map<AssetID, Collection<T>> toJavaMap () {
		Map<AssetID, Collection<T>> map_t = Collections.castMap(map);
		return map_t.toJavaMap();
	}

	@Override
	public void put (AssetID object_name, T object) {
		Set<T> collection = find(object_name);
		collection.add(object);

	}

	private Set<T> find (AssetID object_name) {
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
	public void sortKeys (Comparator<? super AssetID> keysComparator) {
		map.sortKeys(keysComparator);
	}

	@Override
	public EditableCollection<AssetID> cutToSize (int max_size) {
		return this.map.cutToSize(max_size);
	}

}
