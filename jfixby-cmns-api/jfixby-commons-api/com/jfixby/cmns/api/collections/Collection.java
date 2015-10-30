package com.jfixby.cmns.api.collections;

import java.util.Iterator;

public interface Collection<T> extends Iterable<T> {
	public int size();

	public boolean contains(Object element);

	public java.util.List<T> toJavaList();

	public T getElementAt(long i);

	public Iterator<T> iterator();

	public T getLast();

	public List<T> toList();

	public boolean isEmpty();

	public void print(String tag);

	public void print(String tag, int from_index, int to_index);

	public boolean beginsWith(Collection<T> steps);
}
