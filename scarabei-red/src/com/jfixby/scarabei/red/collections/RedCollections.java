
package com.jfixby.scarabei.red.collections;

import java.util.Comparator;
import java.util.Iterator;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.CollectionConverter;
import com.jfixby.scarabei.api.collections.CollectionFilter;
import com.jfixby.scarabei.api.collections.CollectionScanner;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.CollectionsComponent;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.Heap;
import com.jfixby.scarabei.api.collections.Histogramm;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.collections.Pool;
import com.jfixby.scarabei.api.collections.PoolElementsSpawner;
import com.jfixby.scarabei.api.collections.PriorityQueue;
import com.jfixby.scarabei.api.collections.Queue;
import com.jfixby.scarabei.api.collections.Sequence;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.collections.ZxZ_Functuion;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.util.JUtils;

public abstract class RedCollections implements CollectionsComponent {

	@Override
	public abstract <T> List<T> newList ();
	// {
	// java.util.List<T> legacy = newLegacyList();
	// return new RedList<T>(legacy);
	// }

	@Override
	public <T> List<T> newList (final T[] array) {
		final List<T> result = this.newList();
		result.addAllArrayElements(array);
		return result;
	}

	@Override
	public <K, V> Map<K, V> newMap () {
		return new RedHashMap<K, V>();
	}

	@Override
	public <T> Set<T> newSet () {
		final Set<T> result = new RedSet<T>();
		return result;
	}

	@Override
	public <T> Set<T> newSet (final T[] array) {
		final List<T> tmp = this.newList();
		tmp.addAllArrayElements(array);
		final Set<T> result = new RedSet<T>();
		result.addAll(tmp);
		return result;
	}

	@Override
	public <T> List<T> newList (final com.jfixby.scarabei.api.collections.Collection<? extends T> array) {
		final List<T> list = this.newList();
		list.addAll(array);
		return list;
	}

	@Override
	public <T> ZxZ_Functuion<T> newZxZ_Function () {
		return new Red_ZxZ_Map_Functuion<T>();
		// return new ZxZ_Array_Functuion<T>();
	}

	@Override
	public <T> Set<T> newSet (final java.util.Collection<? extends T> collection) {
		final Set<T> result = new RedSet<T>();
		result.addJavaCollection(collection);
		// result.addAllArrayElements(array);
		return result;
	}

	@Override
	public <T> Set<T> newSet (final Collection<? extends T> collection) {
		final Set<T> result = new RedSet<T>();
		result.addAll(collection);
		// result.addAllArrayElements(array);
		return result;
	}

	@Override
	public <T> List<T> newList (final java.util.Collection<? extends T> java_colletion) {
		final List<T> list = this.newList();
		list.addJavaCollection(java_colletion);
		return list;
	}

	@Override
	public <K, V> Map<K, V> newMap (final Mapping<? extends K, ? extends V> map) {
		final Map<K, V> red_map = this.newMap();
		red_map.putAll(map);
		return red_map;
	}

	@Override
	public <K, V> Map<K, V> newMap (final java.util.Map<? extends K, ? extends V> java_map) {
		final Map<K, V> red_map = this.newMap();
		red_map.putJavaMap(java_map);
		return red_map;
	}

	@Override
	public <T> Queue<T> newQueue () {
		return new RedQueue<T>();
	}

	@Override
	public <T> PriorityQueue<T> newPriorityQueue (final Comparator<T> priorityComparator) {
		return new RedPriorityQueue<T>();
	}

	@Override
	public <T> Pool<T> newPool (final PoolElementsSpawner<T> spawner) {
		return new RedPool<T>(spawner);
	}

	@Override
	public <T> void scanCollection (final Iterable<T> collection, final CollectionScanner<? super T> scanner) {
		final Iterator<T> it = collection.iterator();
		for (int i = 0; it.hasNext(); i++) {
			final T element = it.next();
			scanner.scanElement(element, i);
		}
	}

	@Override
	public <T> Heap<T> newHeap (final Comparator<? super T> comparator) {
		return new RedHeap<T>(comparator);
	}

	@Override
	public <T> List<T> filter (final Collection<? extends T> source, final CollectionFilter<? super T> filter) {
		final List<T> result = Collections.newList();
		for (final T t : source) {
			if (filter.fits(t)) {
				result.add(t);
			}
		}
		return result;
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
	public boolean beginsWith (final Collection<?> list, final Collection<?> with) {
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
			final Object a = with.getElementAt(i);
			final Object b = list.getElementAt(i);
// if (!Objects.equals(a, b)) {
			if (!JUtils.equalObjects(a, b)) {
				// L.d("false", a + " != " + b);
				return false;
			}
		}
		return true;

	}

// @SuppressWarnings("unchecked")
// @Override
// public <Q, P, Cp extends EditableCollection<P>> Cp castCollection (final Collection<Q> input, final Cp output) {
// for (final Q i : input) {
// final P p = (P)i;
// output.add(p);
// }
// return output;
// }

// @Override
// public <Q, P> List<P> castCollection (final Collection<Q> input) {
// final List<P> list = Collections.newList();
// return this.castCollection(input, list);
// }

// @SuppressWarnings("unchecked")
// @Override
// public <A, B, X, Y, Mp extends Map<A, B>> Mp castMap (final Mapping<X, Y> input, final Mp output) {
// for (final X iK : input.keys()) {
// final A oK = (A)iK;
// final B oV = (B)input.get(iK);
// output.put(oK, oV);
// }
// return output;
//
// }

// @Override
// public <A, B, X, Y> Map<A, B> castMap (final Mapping<X, Y> input) {
// final Map<A, B> map = Collections.newMap();
// return this.castMap(input, map);
// }

	@Override
	public <T> void arrayCopy (final Collection<? extends T> source, final int source_index,
		final EditableCollection<? super T> destination, final int number_of_elements) {
		for (int i = source_index; i < source_index + number_of_elements; i++) {
			destination.add(source.getElementAt(i));
		}
	}

	@Override
	public <T> Histogramm<T> newHistogramm () {
		return new RedHistogramm<T>();
	}

	@Override
	public <T> List<T> newList (final Iterable<? extends T> java_colletion) {
		final List<T> list = this.newList();
		list.addAll(java_colletion);
		return list;
	}

	@Override
	public <A, B> void convertCollection (final Collection<A> input, final EditableCollection<B> output,
		final CollectionConverter<A, B> converter) {
		final CollectionScanner<A> scanner = new CollectionScanner<A>() {
			@Override
			public void scanElement (final A element, final long index) {
				final B converted = converter.convert(element);
				output.add(converted);
			}
		};
		this.scanCollection(input, scanner);
	}

// @Override
// public <T, A extends EditableCollection<T>> A intersect (final Collection<? extends T> c1, final Collection<? extends T> c2,
// final A target) {
// for (int i = 0; i < c1.size(); i++) {
// final T e = c1.getElementAt(i);
// if (c2.contains(e)) {
// target.add(e);
// }
// }
//
// return target;
// }

	@Override
	public <T> void scanCollection (final Sequence<T> collection, final long fromIndex, final long toIndex,
		final CollectionScanner<? super T> scanner) {
		for (long i = fromIndex; i < toIndex; i++) {
			final T element = collection.getElementAt(i);
			scanner.scanElement(element, i);
		}
	}

	@Override
	public <T> T[] toArray (final Collection<T> c) {
// final T[] foos = x.toArray(new T[x.size()]);

//
		final Object[] array = new Object[c.size()];
		for (int i = 0; i < c.size(); i++) {
			array[i] = c.getElementAt(i);
		}
		return (T[])array;
	}

}
