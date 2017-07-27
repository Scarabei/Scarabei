
package com.jfixby.scarabei.red.collections;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.CollectionFilter;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.IntegerMath;

public class RedSet<T> implements Set<T> {
	final List<T> content_list = Collections.newList();
	final HashSet<T> content_set = new HashSet<T>();

	@Override
	public boolean containsAll (final Collection<?> list) {
		for (int i = 0; i < list.size(); i++) {
			if (!this.contains(list.getElementAt(i))) {
				return false;
			}
		}
		return true;
	}

	public RedSet () {
		super();
	}

	@Override
	public boolean contains (final Object element) {
		return this.content_set.contains(element);
	}

	@Override
	public boolean remove (final Object element) {
		this.content_set.remove(element);
		return this.content_list.remove(element);
	}

	@Override
	public RedSet<T> addAll (final Iterable<? extends T> parts_list) {
		for (final T e : parts_list) {
			this.add(e);
		}
		return this;
	}

	@Override
	public void print (final String tag, final int from_index, final int to_index) {
		final Set<T> l = Collections.newSet();
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
	public int size () {
		return this.content_set.size();
	}

	@Override
	public T getElementAt (final long i) {
		return this.content_list.getElementAt(i);
	}

	@Override
	public Iterator<T> iterator () {
		return this.content_list.iterator();
	}

	@Override
	public boolean add (final T element) {
		boolean first_occurence = false;
		if (!this.content_set.contains(element)) {
			this.content_list.add(element);
			this.content_set.add(element);
			first_occurence = true;
		} else {

		}
		return first_occurence;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.content_list == null) ? 0 : this.content_list.hashCode());
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
		final RedSet<?> other = (RedSet<?>)obj;
		if (this.content_list == null) {
			if (other.content_list != null) {
				return false;
			}
		} else if (!this.content_list.equals(other.content_list)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString () {
		return this.content_list.toString();
	}

	@Override
	public void clear () {
		this.content_list.clear();
		this.content_set.clear();
	}

	@Override
	public int indexOf (final Object element) {
		return this.content_list.indexOf(element);
	}

	@Override
	public java.util.List<T> toJavaList () {
		return this.content_list.toJavaList();
	}

	@Override
	public void print (final String tag) {
		L.d(tag, this);
	}

	@Override
	public T getLast () {
		return this.content_list.getLast();
	}

	@Override
	public void removeAll (final Collection<? extends Object> list) {
		this.content_set.removeAll(list.toJavaList());
		this.content_list.removeAll(list);
	}

	@Override
	public List<T> toList () {
		final List<T> tmp = Collections.newList();
		tmp.addAll(this.content_list);
		return tmp;
	}

	@Override
	public T removeElementAt (final long i) {
		final T element = this.content_list.removeElementAt(i);
		this.content_set.remove(element);
		return element;
	}

	@Override
	public T removeLast () {
		final T element = this.content_list.removeLast();
		this.content_set.remove(element);
		return element;
	}

	@Override
	public RedSet<T> addJavaCollection (final java.util.Collection<? extends T> java_collection) {
		for (final Iterator<? extends T> i = java_collection.iterator(); i.hasNext();) {
			final T element = i.next();
			this.add(element);
		}
		return this;
	}

	@Override
	public void addAllArrayElements (final T[] array) {
		for (int i = 0; i < array.length; i++) {
			this.content_list.add(array[i]);
			this.content_set.add(array[i]);
		}

	}

	@Override
	public boolean isEmpty () {
		return this.content_list.isEmpty();
	}

	@Override
	public void sort (final Comparator<? super T> comparator) {
		this.content_list.sort(comparator);
	}

	@Override
	public void sort () {
		this.sort(null);
	}

	@Override
	public void reverse () {
		this.content_list.reverse();
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
			return Collections.newList();
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
		return this.content_list.filter(filter);
	}

}
