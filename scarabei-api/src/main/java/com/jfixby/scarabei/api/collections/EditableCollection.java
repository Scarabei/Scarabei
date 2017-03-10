
package com.jfixby.scarabei.api.collections;

import java.util.Comparator;

public interface EditableCollection<T> extends Collection<T> {

	// ---basic-----------------------------------------

	public boolean remove (Object element);

	public void clear ();

	public boolean add (T element);

	public void reverse ();

	public T removeElementAt (long i);

	public T removeLast ();

	public EditableCollection<T> addJavaCollection (java.util.Collection<? extends T> java_collection);

	public void addAllArrayElements (T[] array);

	public EditableCollection<T> addAll (Iterable<? extends T> list);

	public void removeAll (Collection<? extends Object> list);

	public void sort (Comparator<? super T> comparator);

	public void sort ();

	public EditableCollection<T> splitAt (int index);

}
