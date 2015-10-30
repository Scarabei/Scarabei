package com.jfixby.cmns.jutils.desktop;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.IntegerMath;

public class DesktopSet<T> implements Set<T> {
	final DesktopList<T> content_list = new DesktopList<T>();
	final HashSet<T> content_set = new HashSet<T>();

	@Override
	public boolean contains(Object element) {
		return content_set.contains(element);
	}

	@Override
	public boolean remove(Object element) {
		content_set.remove(element);
		return content_list.remove(element);
	}

	@Override
	public void print(String tag, int from_index, int to_index) {
		Set<T> l = JUtils.newSet();
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
	public int size() {
		return content_set.size();
	}

	@Override
	public T getElementAt(long i) {
		return content_list.getElementAt(i);
	}

	@Override
	public void addAll(Collection<? extends T> list) {
		for (Iterator<? extends T> i = list.iterator(); i.hasNext();) {
			T element = i.next();
			this.add(element);
		}
	}

	@Override
	public Iterator<T> iterator() {
		return content_list.iterator();
	}

	@Override
	public boolean add(T element) {
		boolean first_occurence = false;
		if (!content_set.contains(element)) {
			content_list.add(element);
			content_set.add(element);
			first_occurence = true;
		} else {

		}
		return first_occurence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((content_list == null) ? 0 : content_list.hashCode());
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
		DesktopSet<?> other = (DesktopSet<?>) obj;
		if (content_list == null) {
			if (other.content_list != null)
				return false;
		} else if (!content_list.equals(other.content_list))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return content_list.toString();
	}

	@Override
	public void clear() {
		this.content_list.clear();
		this.content_set.clear();
	}

	@Override
	public int indexOf(Object element) {
		return this.content_list.indexOf(element);
	}

	@Override
	public java.util.List<T> toJavaList() {
		return this.content_list.toJavaList();
	}

	@Override
	public void print(String tag) {
		L.d(tag, this);
	}

	@Override
	public T getLast() {
		return this.content_list.getLast();
	}

	@Override
	public void removeAll(Collection<? extends Object> list) {
		this.content_set.removeAll(list.toJavaList());
		this.content_list.removeAll(list);
	}

	@Override
	public List<T> toList() {
		final List<T> tmp = JUtils.newList();
		tmp.addAll(this.content_list);
		return tmp;
	}

	@Override
	public T removeElementAt(final long i) {
		final T element = this.content_list.removeElementAt(i);
		this.content_set.remove(element);
		return element;
	}

	@Override
	public T removeLast() {
		final T element = this.content_list.removeLast();
		this.content_set.remove(element);
		return element;
	}

	@Override
	public void addJavaCollection(
			java.util.Collection<? extends T> java_collection) {
		for (Iterator<? extends T> i = java_collection.iterator(); i.hasNext();) {
			T element = i.next();
			this.add(element);
		}
	}

	@Override
	public void addAllArrayElements(T[] array) {
		for (int i = 0; i < array.length; i++) {
			this.content_list.add(array[i]);
			this.content_set.add(array[i]);
		}

	}

	@Override
	public boolean isEmpty() {
		return this.content_list.isEmpty();
	}

	@Override
	public void sort(Comparator<T> comparator) {
		this.content_list.sort(comparator);
	}

	@Override
	public void reverse() {
		content_list.reverse();
	}

	@Override
	public boolean beginsWith(Collection<T> steps) {
		return JUtils.listBeginsWith(this, steps);
	}

}
