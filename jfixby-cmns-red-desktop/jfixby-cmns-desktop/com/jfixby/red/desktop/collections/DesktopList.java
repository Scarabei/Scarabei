package com.jfixby.red.desktop.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.IntegerMath;

public class DesktopList<T> implements List<T> {
	private final LinkedList<T> legacy = new LinkedList<T>();

	@Override
	public void addAllArrayElements(T[] array) {
		if (array == null) {
			throw new Error("Input array is null");
		}
		for (int i = 0; i < array.length; i++) {
			legacy.add(array[i]);
		}
	}

	@Override
	public boolean contains(Object element) {
		return this.legacy.contains(element);
	}

	@Override
	public boolean remove(Object element) {
		return this.legacy.remove(element);
	}

	@Override
	public int size() {
		return this.legacy.size();
	}

	@Override
	public T getElementAt(long i) {
		return this.legacy.get((int) i);
	}

	@Override
	public void addAll(Collection<? extends T> parts_list) {
		this.legacy.addAll(parts_list.toJavaList());

	}

	// ----------------------------------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((legacy == null) ? 0 : legacy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DesktopList<?> other = (DesktopList<?>) obj;
		if (legacy == null) {
			if (other.legacy != null)
				return false;
		} else if (!legacy.equals(other.legacy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return legacy.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return this.legacy.iterator();
	}

	@Override
	public boolean add(T element) {
		this.legacy.add(element);
		return true;
	}

	public void clear() {
		this.legacy.clear();
	}

	@Override
	public T removeLast() {
		if (this.size() == 0) {
			throw new Error("List is empty!");
		}
		return this.legacy.remove(this.size() - 1);
	}

	@Override
	public void print(String tag) {
		L.d(tag, this);
	}

	@Override
	public void print(String tag, int from_index, int to_index) {
		List<T> l = Collections.newList();
		int N = this.size();
		int a = 0;
		int b = N;
		a = (int) IntegerMath.limit(0, from_index, N);
		b = (int) IntegerMath.limit(0, to_index, N);

		int d = 1;
		if (a > b) {
			d = -1;
		}
		for (int i = a; i < b; i = i + d) {
			T element = this.getElementAt(i);
			l.add(element);
		}
		l.print(tag);
	}

	@Override
	public T getLast() {
		if (this.size() == 0) {
			throw new Error("List is empty!");
		}
		return this.legacy.get(this.size() - 1);
	}

	@Override
	public T removeElementAt(long i) {
		return this.legacy.remove((int) i);
	}

	@Override
	public int indexOf(Object element) {
		return this.legacy.indexOf(element);
	}

	@Override
	public void removeAll(Collection<? extends Object> collection) {
		this.legacy.removeAll(collection.toJavaList());
	}

	@Override
	public void insertElementAt(T element, int iindex) {
		this.legacy.add(iindex, element);
	}

	@Override
	public T setElementAt(T element, int iindex) {
		return this.legacy.set(iindex, element);
	}

	@Override
	public java.util.List<T> toJavaList() {
		java.util.ArrayList<T> result = new java.util.ArrayList<T>();
		result.addAll(this.legacy);
		return result;
	}

	@Override
	public void addJavaCollection(java.util.Collection<? extends T> java_collection) {
		this.legacy.addAll(java_collection);
	}

	@Override
	public List<T> toList() {
		return this;
	}

	@Override
	public boolean isEmpty() {
		return this.legacy.isEmpty();
	}

	@Override
	public void sort(Comparator<T> comparator) {
		legacy.sort(comparator);
	}

	@Override
	public void reverse() {
		java.util.Collections.reverse(legacy);
	}

	@Override
	public boolean beginsWith(Collection<T> steps) {
		return Collections.listBeginsWith(this, steps);
	}

	@Override
	public EditableCollection<T> splitAt(int index) {
		if (index < 0) {
			Err.reportError("index(" + index + ") < 0");
		}
		final int size = this.size();
		if (index >= size) {
			return this;
		}

		final List<T> tail = Collections.newList();
		for (int i = index; i < size; i++) {
			T e = this.removeElementAt(index);
			tail.add(e);
		}

		return tail;
	}

	@Override
	public void sort() {
		this.sort(null);
	}
}
