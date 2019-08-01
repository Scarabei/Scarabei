
package com.jfixby.scarabei.api.collections;

public interface Collection<T> extends Iterable<T>, Sequence<T> {
	public int size ();

	public boolean contains (Object element);

	public java.util.List<T> toJavaList ();

	public T getLast ();

	public List<T> toList ();

	public boolean isEmpty ();

	public boolean beginsWith (Collection<T> steps);

	public List<T> filter (CollectionFilter<? super T> filter);

	public boolean containsAll (Collection<?> other);

	public int indexOf (Object element);

}
