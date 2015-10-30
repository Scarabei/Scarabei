package com.jfixby.cmns.api.collections;

import java.util.Comparator;

public interface EditableCollection<T> extends Collection<T> {

	// ---basic-----------------------------------------

	public boolean remove(Object element);

	public void clear();

	public boolean add(T element);

	public int indexOf(Object element);

	public void reverse();

	public T removeElementAt(long i);

	public T removeLast();

	public void addJavaCollection(
			java.util.Collection<? extends T> java_collection);

	public void addAllArrayElements(T[] array);

	public void addAll(Collection<? extends T> list);

	public void removeAll(Collection<? extends Object> list);

	public void sort(Comparator<T> comparator);

}
