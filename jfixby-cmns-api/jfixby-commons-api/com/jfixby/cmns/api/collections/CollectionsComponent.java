
package com.jfixby.cmns.api.collections;

public interface CollectionsComponent {

	<T> List<T> newList ();

	<T> Set<T> newSet ();

	<T> List<T> newList (T... array);

	<T> Set<T> newSet (T... array);

	<T> List<T> newList (Collection<? extends T> collection);

	<T> Set<T> newSet (Collection<? extends T> collection);

	<T> List<T> newList (java.util.Collection<? extends T> java_colletion);

	<T> List<T> newList (Iterable<? extends T> java_colletion);

	<T> Set<T> newSet (java.util.Collection<? extends T> java_colletion);

	<K, V> Map<K, V> newMap ();

	<K, V> Map<K, V> newMap (Mapping<? extends K, ? extends V> map);

	<K, V> Map<K, V> newMap (java.util.Map<? extends K, ? extends V> java_map);

	public <T> ZxZ_Functuion<T> newZxZ_Function ();

	public <T> Queue<T> newQueue ();

	public <T> Pool<T> newPool (PoolElementsSpawner<T> spawner);

	public <T> void scanCollection (Collection<? extends T> collection, CollectionScanner<? super T> scanner);

	public <A, B> void convertCollection (Collection<? extends A> input, EditableCollection<? super B> output,
		CollectionConverter<A, B> converter);

	boolean equalLists (List<?> a, List<?> b);

	public <A, B, X, Y> Map<A, B> castMap (Mapping<X, Y> input);

	public <A, B, X, Y, Mp extends Map<A, B>> Mp castMap (Mapping<X, Y> input, Mp output);

	boolean beginsWith (Collection<?> list, Collection<?> with);

	public <Q, P, Cp extends EditableCollection<P>> Cp castCollection (Collection<Q> input, Cp output);

	public <Q, P> List<P> castCollection (Collection<Q> input);

	public <T> void arrayCopy (Collection<? extends T> source, int source_index, EditableCollection<? super T> destination,
		int number_of_elements);

	public <T> List<T> filter (Collection<? extends T> source, CollectionFilter<? super T> filter);

	public <T> Histogramm<T> newHistogramm ();

	// public <T> λFunction<Collection<T>, Collection<T>>
	// MERGE_SORT(Comparator<? super T> comparator);
	//
	// public <T> λFunction<Collection<T>, Collection<T>> MERGE_SORT();

}
