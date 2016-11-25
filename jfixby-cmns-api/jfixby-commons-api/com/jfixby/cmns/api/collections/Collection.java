
package com.jfixby.cmns.api.collections;

public interface Collection<T> extends Iterable<T>, Sequence<T> {
	public int size ();

	public boolean contains (Object element);

	public java.util.List<T> toJavaList ();

	public T getLast ();

	public List<T> toList ();

	public boolean isEmpty ();

	public void print (String tag);

	public void print (String tag, int from_index, int to_index);

	public boolean beginsWith (Collection<T> steps);

	public List<T> filter (CollectionFilter<? super T> filter);

	public boolean containsAll (Collection<?> other);

	public int indexOf (Object element);

}
