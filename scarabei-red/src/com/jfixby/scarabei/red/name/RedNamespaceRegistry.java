
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
	public void put (final ID object_name, final Collection<T> value) {
		final Set<T> collection = this.find(object_name);
		collection.addAll(value);
	}

	@Override
	public void clear () {
		this.map.clear();
	}

	@Override
	public Collection<T> remove (final Object key) {
		return this.map.remove(key);
	}

	@Override
	public void putAll (final Mapping<? extends ID, ? extends Collection<T>> other_map) {
		for (final ID key : other_map.keys()) {
			final Collection<T> value = other_map.get(key);
			this.put(key, value);
		}

	}

	@Override
	public void putJavaMap (final java.util.Map<? extends ID, ? extends Collection<T>> java_map) {
		for (final ID key : java_map.keySet()) {
			final Collection<T> value = java_map.get(key);
			this.put(key, value);
		}
	}

	@Override
	public void removeAll (final Collection<?> keys) {
		this.map.removeAll(keys);
	}

	@Override
	public boolean containsKey (final Object key) {
		return this.map.containsKey(key);
	}

	@Override
	public Collection<T> get (final Object key) {
		final Collection<T> result = this.map.get(key);

		if (result != null) {
			return result;
		}

		ID id = (ID)key;
		final String last = id.getLastStep();
		if ("*".equals(last)) {
			id = id.parent();
			return this.wildcardGet(id);
		}

		return null;

	}

	private Collection<T> wildcardGet (final ID id) {
		final List<T> result = Collections.newList();
		for (int i = 0; i < this.map.size(); i++) {
			final ID k = this.map.keys().getElementAt(i);
			if (id.includes(k)) {
				final Collection<T> vals = this.map.get(k);
				result.addAll(vals);
			}
		}
		// result.print("" + id);
		return result;
	}

	@Override
	public void print (final String tag) {
		this.map.print(tag);
	}

	@Override
	public int size () {
		return this.map.size();
	}

	@Override
	public ID getKeyAt (final long i) {
		return this.map.getKeyAt(i);
	}

	@Override
	public Collection<T> getValueAt (final long i) {
		return this.map.getValueAt(i);
	}

	@Override
	public Iterator<ID> keysIterator () {
		return this.map.keysIterator();
	}

	@Override
	public Collection<Collection<T>> values () {
		final Collection<Set<T>> vals = this.map.values();
		final Collection<?> obj = vals;
		final Collection<Collection<T>> x = (Collection<Collection<T>>)obj;
		return x;
	}

	@Override
	public Collection<ID> keys () {
		return this.map.keys();
	}

	@Override
	public java.util.Map<ID, Collection<T>> toJavaMap () {
		final Map<ID, ?> map = this.map;
		final Map<ID, Collection<T>> map_t = (Map<ID, Collection<T>>)map;
		return map_t.toJavaMap();
	}

	@Override
	public void put (final ID object_name, final T object) {
		final Set<T> collection = this.find(object_name);
		collection.add(object);

	}

	private Set<T> find (final ID object_name) {
		Set<T> collection = this.map.get(object_name);
		if (collection == null) {
			collection = Collections.newSet();
			this.map.put(object_name, collection);
		}
		return collection;
	}

	@Override
	public Collection<T> allValues () {
		final List<T> result = Collections.newList();
		for (int i = 0; i < this.map.size(); i++) {
			final Set<T> values = this.map.getValueAt(i);
			result.addAll(values);
		}
		return result;
	}

	@Override
	public void sortKeys () {
		this.map.sortKeys();
	}

	@Override
	public void sortKeys (final Comparator<? super ID> keysComparator) {
		this.map.sortKeys(keysComparator);
	}

	@Override
	public EditableCollection<ID> cutToSize (final int max_size) {
		return this.map.cutToSize(max_size);
	}

}
