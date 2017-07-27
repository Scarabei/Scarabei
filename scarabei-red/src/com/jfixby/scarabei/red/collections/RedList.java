
package com.jfixby.scarabei.red.collections;

import java.util.Iterator;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.CollectionFilter;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.IntegerMath;

public abstract class RedList<T> implements List<T> {
	public final java.util.List<T> legacy;

	public RedList (final java.util.List<T> legacy_list) {
		this.legacy = legacy_list;
	}

	@Override
	public void addAllArrayElements (final T[] array) {
		if (array == null) {
			Err.reportError("Input array is null");
		}
		for (int i = 0; i < array.length; i++) {
			this.legacy.add(array[i]);
		}
	}

	@Override
	public boolean contains (final Object element) {
		return this.legacy.contains(element);
	}

	@Override
	public boolean containsAll (final Collection<?> list) {
		for (int i = 0; i < list.size(); i++) {
			if (!this.contains(list.getElementAt(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean remove (final Object element) {
		return this.legacy.remove(element);
	}

	@Override
	public int size () {
		return this.legacy.size();
	}

	@Override
	public T getElementAt (final long i) {
		return this.legacy.get((int)i);
	}

	@Override
	public RedList<T> addAll (final Iterable<? extends T> parts_list) {
		for (final T e : parts_list) {
			this.add(e);
		}
		return this;
	}

	// ----------------------------------------------------------------------------------------
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.legacy == null) ? 0 : this.legacy.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedList<?> other = (RedList<?>)obj;
		if (this.legacy == null) {
			if (other.legacy != null) {
				return false;
			}
		} else if (!this.legacy.equals(other.legacy)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString () {
		return this.legacy.toString();
	}

	@Override
	public Iterator<T> iterator () {
		return this.legacy.iterator();
	}

	@Override
	public boolean add (final T element) {
		this.legacy.add(element);
		return true;
	}

	@Override
	public void clear () {
		this.legacy.clear();
	}

	@Override
	public T removeLast () {
		if (this.size() == 0) {
			Err.reportError("List is empty!");
		}
		return this.legacy.remove(this.size() - 1);
	}

	@Override
	public void print (final String tag) {
		L.d(tag, this);
	}

	@Override
	public void print (final String tag, final int from_index, final int to_index) {
		final List<T> l = Collections.newList();
		final int N = this.size();
		int a = 0;
		int b = N;
		a = (int)IntegerMath.limit(0, from_index, N);
		b = (int)IntegerMath.limit(0, to_index, N);

		int d = 1;
		if (a > b) {
			d = -1;
		}
		for (int i = a; i < b; i = i + d) {
			final T element = this.getElementAt(i);
			l.add(element);
		}
		l.print(tag);
	}

	@Override
	public T getLast () {
		if (this.size() == 0) {
			Err.reportError("List is empty!");
		}
		return this.legacy.get(this.size() - 1);
	}

	@Override
	public T removeElementAt (final long i) {
		return this.legacy.remove((int)i);
	}

	@Override
	public int indexOf (final Object element) {
		return this.legacy.indexOf(element);
	}

	@Override
	public void removeAll (final Collection<? extends Object> collection) {
		this.legacy.removeAll(collection.toJavaList());
	}

	@Override
	public void insertElementAt (final T element, final int iindex) {
		this.legacy.add(iindex, element);
	}

	@Override
	public void insertAllAt (final Collection<? extends T> aj, final int index) {
		this.legacy.addAll(index, aj.toJavaList());
	}

	@Override
	public T setElementAt (final T element, final int iindex) {
		return this.legacy.set(iindex, element);
	}

	@Override
	public java.util.List<T> toJavaList () {
		final java.util.ArrayList<T> result = new java.util.ArrayList<T>();
		result.addAll(this.legacy);
		return result;
	}

	@Override
	public RedList<T> addJavaCollection (final java.util.Collection<? extends T> java_collection) {
		this.legacy.addAll(java_collection);
		return this;
	}

	@Override
	public List<T> toList () {
		return this;
	}

	@Override
	public boolean isEmpty () {
		return this.legacy.isEmpty();
	}

	@Override
	public void reverse () {
		java.util.Collections.reverse(this.legacy);
	}

	@Override
	public boolean beginsWith (final Collection<T> steps) {
		return Collections.listBeginsWith(this, steps);
	}

	@Override
	public EditableCollection<T> splitAt (final int index) {
		if (index < 0) {
			Err.reportError("index(" + index + ") < 0");
		}
		final int size = this.size();
		if (index >= size) {
			return this;
		}

		final List<T> tail = Collections.newList();
		for (int i = index; i < size; i++) {
			final T e = this.removeElementAt(index);
			tail.add(e);
		}

		return tail;
	}

	@Override
	public List<T> filter (final CollectionFilter<? super T> filter) {
		return Collections.filter(this, filter);
	}

}
