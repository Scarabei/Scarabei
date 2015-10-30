package com.jfixby.cmns.api.collections;

import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.MountPoint;
import com.jfixby.cmns.api.path.RelativePath;

public interface UtilsComponent {

	<T> List<T> newList();

	<T> Set<T> newSet();

	<T> List<T> newList(T... array);

	<T> Set<T> newSet(T... array);

	<T> List<T> newList(Collection<? extends T> collection);

	<T> Set<T> newSet(Collection<? extends T> collection);

	<T> List<T> newList(java.util.Collection<? extends T> java_colletion);

	<T> Set<T> newSet(java.util.Collection<? extends T> java_colletion);

	<K, V> Map<K, V> newMap();

	<K, V> Map<K, V> newMap(Mapping<? extends K, ? extends V> map);

	<K, V> Map<K, V> newMap(java.util.Map<? extends K, ? extends V> java_map);

	RelativePath newRelativePath(String path_string);

	<T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point,
			RelativePath relative);

	<T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point);

	RelativePath newRelativePath(List<String> steps_list);

	RelativePath newRelativePath();

	public <T> ZxZ_Functuion<T> newZxZ_Function();

	public <T> Queue<T> newQueue();

	public <T> Pool<T> newPool(PoolElementsSpawner<T> spawner);

	public <T> void scanCollection(Collection<? extends T> collection,
			CollectionScanner<T> scanner);

	boolean equalLists(List<?> a, List<?> b);

	<T> StateSwitcher<T> newStateSwitcher(T default_state);

	<T> T checkNull(String parameter_name, T value);

	<T> T checkNull(T value);

	void checkEmpty(String parameter_name, String value);

	List<Float> newList(float[] floats);

	List<String> split(String input_string, String splitter);

	// public <A extends Collection<A>, B extends A> B castCollection(A input);
	//
	// public <K, V, A extends Map<K, V>, B extends A> B castCollection(A
	// input);

	public <A, B, X, Y> Map<A, B> castMap(Mapping<X, Y> input);

	public <A, B, X, Y, Mp extends Map<A, B>> Mp castMap(Mapping<X, Y> input,
			Mp output);

	boolean beginsWith(Collection<?> list, Collection<?> with);

	public <Q, P, Cp extends EditableCollection<P>> Cp castCollection(
			Collection<Q> input, Cp output);

	public <Q, P> List<P> castCollection(Collection<Q> input);

}
