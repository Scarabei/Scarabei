
package com.jfixby.red.collections;

import java.util.Objects;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.CollectionConverter;
import com.jfixby.cmns.api.collections.CollectionFilter;
import com.jfixby.cmns.api.collections.CollectionScanner;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.CollectionsComponent;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.Histogramm;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.collections.Mapping;
import com.jfixby.cmns.api.collections.Pool;
import com.jfixby.cmns.api.collections.PoolElementsSpawner;
import com.jfixby.cmns.api.collections.Queue;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.collections.ZxZ_Functuion;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.util.JUtils;

public abstract class RedCollections implements CollectionsComponent {

	@Override
	public abstract <T> List<T> newList ();
	// {
	// java.util.List<T> legacy = newLegacyList();
	// return new RedList<T>(legacy);
	// }

	@Override
	public <T> List<T> newList (T[] array) {
		List<T> result = newList();
		result.addAllArrayElements(array);
		return result;
	}

	@Override
	public <K, V> Map<K, V> newMap () {
		return new RedHashMap<K, V>();
	}

	@Override
	public <T> Set<T> newSet () {
		Set<T> result = new RedSet<T>();
		return result;
	}

	@Override
	public <T> Set<T> newSet (T[] array) {
		List<T> tmp = newList();
		tmp.addAllArrayElements(array);
		Set<T> result = new RedSet<T>();
		result.addAll(tmp);
		return result;
	}

	@Override
	public <T> List<T> newList (com.jfixby.cmns.api.collections.Collection<? extends T> array) {
		List<T> list = this.newList();
		list.addAll(array);
		return list;
	}

	@Override
	public <T> ZxZ_Functuion<T> newZxZ_Function () {
		return new Red_ZxZ_Map_Functuion<T>();
		// return new ZxZ_Array_Functuion<T>();
	}

	@Override
	public <T> Set<T> newSet (java.util.Collection<? extends T> collection) {
		Set<T> result = new RedSet<T>();
		result.addJavaCollection(collection);
		// result.addAllArrayElements(array);
		return result;
	}

	@Override
	public <T> Set<T> newSet (Collection<? extends T> collection) {
		Set<T> result = new RedSet<T>();
		result.addAll(collection);
		// result.addAllArrayElements(array);
		return result;
	}

	@Override
	public <T> List<T> newList (java.util.Collection<? extends T> java_colletion) {
		List<T> list = this.newList();
		list.addJavaCollection(java_colletion);
		return list;
	}

	@Override
	public <K, V> Map<K, V> newMap (Mapping<? extends K, ? extends V> map) {
		Map<K, V> red_map = newMap();
		red_map.putAll(map);
		return red_map;
	}

	@Override
	public <K, V> Map<K, V> newMap (java.util.Map<? extends K, ? extends V> java_map) {
		Map<K, V> red_map = newMap();
		red_map.putJavaMap(java_map);
		return red_map;
	}

	@Override
	public <T> Queue<T> newQueue () {
		return new RedQueue<T>();
	}

	@Override
	public <T> Pool<T> newPool (PoolElementsSpawner<T> spawner) {
		return new RedPool<T>(spawner);
	}

	@Override
	public <T> void scanCollection (Collection<? extends T> collection, CollectionScanner<? super T> scanner) {
		for (int i = 0; i < collection.size(); i++) {
			T element = collection.getElementAt(i);
			scanner.scanElement(element, i, collection);
		}
	}

	@Override
	final public boolean equalLists (final List<?> a, final List<?> b) {
		if (a.size() != b.size()) {
			return false;
		}
		final int N = a.size();
		for (int i = 0; i < N; i++) {
			final Object ai = a.getElementAt(i);
			final Object bi = b.getElementAt(i);
			if (!JUtils.component().equalObjects(ai, bi)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean beginsWith (Collection<?> list, Collection<?> with) {
		Debug.checkNull("list", list);
		Debug.checkNull("with", with);
		if (list.size() < with.size()) {
			return false;
		}
		if (list.equals(with)) {
			return true;
		}
		// L.d("compare");
		// list.print("a");
		// with.print("b");

		for (int i = 0; i < with.size(); i++) {
			Object a = with.getElementAt(i);
			Object b = list.getElementAt(i);
			if (!Objects.equals(a, b)) {
				// L.d("false", a + " != " + b);
				return false;
			}
		}
		return true;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <Q, P, Cp extends EditableCollection<P>> Cp castCollection (Collection<Q> input, Cp output) {
		for (Q i : input) {
			P p = (P)i;
			output.add(p);
		}
		return output;
	}

	@Override
	public <Q, P> List<P> castCollection (Collection<Q> input) {
		List<P> list = Collections.newList();
		return this.castCollection(input, list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A, B, X, Y, Mp extends Map<A, B>> Mp castMap (Mapping<X, Y> input, Mp output) {
		for (X iK : input.keys()) {
			A oK = (A)iK;
			B oV = (B)input.get(iK);
			output.put(oK, oV);
		}
		return output;

	}

	@Override
	public <A, B, X, Y> Map<A, B> castMap (Mapping<X, Y> input) {
		final Map<A, B> map = Collections.newMap();
		return this.castMap(input, map);
	}

	@Override
	public <T> void arrayCopy (final Collection<? extends T> source, final int source_index,
		EditableCollection<? super T> destination, final int number_of_elements) {
		for (int i = source_index; i < source_index + number_of_elements; i++) {
			destination.add(source.getElementAt(i));
		}
	}

	@Override
	public <T> Histogramm<T> newHistogramm () {
		return new RedHistogramm<T>();
	}

	@Override
	public <T> List<T> newList (Iterable<? extends T> java_colletion) {
		List<T> list = this.newList();
		list.addAll(java_colletion);
		return list;
	}

	@Override
	public <T> List<T> filter (Collection<? extends T> source, CollectionFilter<? super T> filter) {
		List<T> result = Collections.newList();
		for (T t : source) {
			if (filter.fits(t)) {
				result.add(t);
			}
		}
		return result;
	}

	@Override
	public <A, B> void convertCollection (final Collection<? extends A> input, final EditableCollection<? super B> output,
		final CollectionConverter<A, B> converter) {
		CollectionScanner<A> scanner = new CollectionScanner<A>() {
			@Override
			public void scanElement (A element, int index, Collection<? extends A> collection) {
				B converted = converter.convert(element);
				output.add(converted);
			}
		};
		this.scanCollection(input, scanner);
	}

}
