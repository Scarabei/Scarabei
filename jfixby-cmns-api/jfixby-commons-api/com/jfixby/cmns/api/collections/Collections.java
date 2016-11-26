
package com.jfixby.cmns.api.collections;

import java.util.Comparator;

import com.jfixby.cmns.api.ComponentInstaller;

public class Collections {

	static private ComponentInstaller<CollectionsComponent> componentInstaller = new ComponentInstaller<CollectionsComponent>(
		"Collections");

	public static final void installComponent (final CollectionsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final CollectionsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final CollectionsComponent component () {
		return componentInstaller.getComponent();
	}

	public static final <T> List<T> newList () {
		return invoke().newList();
	}

	public static final <T> List<T> newList (final T... array) {
		return invoke().newList(array);
	}

	public static final <T> List<T> newList (final Collection<? extends T> collection) {
		return invoke().newList(collection);
	}

	public static final <T> List<T> newList (final java.util.Collection<? extends T> java_colletion) {
		return invoke().newList(java_colletion);
	}

	public static final <T> List<T> newList (final Iterable<? extends T> java_colletion) {
		return invoke().newList(java_colletion);
	}

	public static final <K, V> Map<K, V> newMap () {
		return invoke().newMap();
	}

	public static final <K, V> Map<K, V> newMap (final java.util.Map<K, V> java_map) {
		return invoke().newMap(java_map);
	}

	public static final <K, V> Map<K, V> newMap (final Mapping<? extends K, ? extends V> other_map) {
		return invoke().newMap(other_map);
	}

	public static final <T> Set<T> newSet () {
		return invoke().newSet();
	}

	public static <T> Set<T> newSet (final T... array) {
		return invoke().newSet(array);
	}

	public static <T> Set<T> newSet (final java.util.Collection<? extends T> java_colletion) {
		return invoke().newSet(java_colletion);
	}

	public static final <T> Set<T> newSet (final Collection<? extends T> array) {
		return invoke().newSet(array);
	}

	public static final <T> ZxZ_Functuion<T> newZxZ_Function () {
		return invoke().newZxZ_Function();
	}

	public static <T> Heap<T> newHeap (final Comparator<? super T> comparator) {
		return invoke().newHeap(comparator);
	}

	public static <T> Queue<T> newQueue () {
		return invoke().newQueue();
	}

	public static <T> PriorityQueue<T> newPriorityQueue (final Comparator<T> priorityComparator) {
		return invoke().newPriorityQueue(priorityComparator);
	}

	public static <T> Pool<T> newPool (final PoolElementsSpawner<T> spawner) {
		return invoke().newPool(spawner);
	}

	public static <T> void scanCollection (final Iterable<T> collection, final CollectionScanner<? super T> scanner) {
		invoke().scanCollection(collection, scanner);
	}

	public static <T> void scanCollection (final Sequence<T> collection, final long fromIndex, final long toIndex,
		final CollectionScanner<? super T> scanner) {
		invoke().scanCollection(collection, fromIndex, toIndex, scanner);
	}

	public static boolean equalLists (final List<?> A, final List<?> B) {
		return invoke().equalLists(A, B);
	}

	public static boolean listBeginsWith (final Collection<?> list, final Collection<?> with) {
		return invoke().beginsWith(list, with);
	}

	static public <Q, P, Cp extends EditableCollection<P>> Cp castCollection (final Collection<Q> input, final Cp output) {
		return invoke().castCollection(input, output);
	}

	static public <Q, P> List<P> castCollection (final Collection<Q> input) {
		return invoke().castCollection(input);
	}

	public static <A, B, X, Y, Mp extends Map<A, B>> Mp castMap (final Mapping<X, Y> input, final Mp output) {
		return invoke().castMap(input, output);
	}

	public static <A, B, X, Y> Map<A, B> castMap (final Mapping<X, Y> input) {
		return invoke().castMap(input);
	}

	public static <T> void arrayCopy (final Collection<? extends T> source, final int source_index,
		final EditableCollection<? super T> destination, final int number_of_elements) {
		invoke().arrayCopy(source, source_index, destination, number_of_elements);
	}

	public static <T> List<T> filter (final Collection<? extends T> source, final CollectionFilter<? super T> filter) {
		return invoke().filter(source, filter);
	}

	public static <T> Histogramm<T> newHistogramm () {
		return invoke().newHistogramm();
	}

	public static <A, B> void convertCollection (final Collection<A> input, final EditableCollection<B> output,
		final CollectionConverter<A, B> converter) {
		invoke().convertCollection(input, output, converter);
	}

	public static <T, A extends EditableCollection<T>> A intersect (final Collection<? extends T> c1,
		final Collection<? extends T> c2, final A target) {
		return invoke().intersect(c1, c2, target);
	}

}
