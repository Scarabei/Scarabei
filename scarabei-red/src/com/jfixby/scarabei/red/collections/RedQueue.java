
package com.jfixby.scarabei.red.collections;

import java.util.Iterator;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.CollectionFilter;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Queue;

public class RedQueue<T> implements Queue<T> {

	final List<T> queue = Collections.newList();

	@Override
	public List<T> filter (final CollectionFilter<? super T> filter) {
		return this.queue.filter(filter);
	}

	public RedQueue () {

	}

	@Override
	public boolean containsAll (final Collection<?> list) {
		return this.queue.containsAll(list);
	}

	@Override
	public void enqueue (final T element) {
		this.queue.add(element);
	}

	@Override
	public boolean hasMore () {
		return this.queue.size() > 0;
	}

	@Override
	public T dequeue () {
		return this.queue.removeElementAt(0);
	}

	@Override
	public void enqueueAll (final Collection<T> elements) {
		this.queue.addAll(elements);
	}

	@Override
	public int size () {
		return this.queue.size();
	}

	@Override
	public boolean contains (final Object element) {
		return this.queue.contains(element);
	}

	@Override
	public java.util.List<T> toJavaList () {
		return this.queue.toJavaList();
	}

	@Override
	public T getElementAt (final long i) {
		return this.queue.getElementAt(i);
	}

	@Override
	public Iterator<T> iterator () {
		return this.queue.iterator();
	}

	@Override
	public T getLast () {
		return this.queue.getLast();
	}

	@Override
	public List<T> toList () {
		return this.queue.toList();
	}

	@Override
	public boolean isEmpty () {
		return this.queue.isEmpty();
	}

	@Override
	public void print (final String tag) {
		this.queue.print(tag);
	}

	@Override
	public void print (final String tag, final int from_index, final int to_index) {
		this.queue.print(tag, from_index, to_index);
	}

	@Override
	public boolean beginsWith (final Collection<T> steps) {
		return this.queue.beginsWith(steps);
	}

	@Override
	public void clear () {
		this.queue.clear();
	}

	@Override
	public int indexOf (final Object element) {
		return this.queue.indexOf(element);
	}

}
