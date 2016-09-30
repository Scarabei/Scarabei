
package com.jfixby.red.collections;

import java.util.Iterator;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.CollectionFilter;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Queue;

public class RedQueue<T> implements Queue<T> {

	final List<T> queue = Collections.newList();

	@Override
	public List<T> filter (CollectionFilter<? super T> filter) {
		return this.queue.filter(filter);
	}

	public RedQueue () {

	}

	@Override
	public void enqueue (T element) {
		queue.add(element);
	}

	@Override
	public boolean hasMore () {
		return queue.size() > 0;
	}

	@Override
	public T dequeue () {
		return queue.removeElementAt(0);
	}

	@Override
	public void enqueueAll (Collection<T> elements) {
		queue.addAll(elements);
	}

	@Override
	public int size () {
		return queue.size();
	}

	@Override
	public boolean contains (Object element) {
		return queue.contains(element);
	}

	@Override
	public java.util.List<T> toJavaList () {
		return queue.toJavaList();
	}

	@Override
	public T getElementAt (long i) {
		return queue.getElementAt(i);
	}

	@Override
	public Iterator<T> iterator () {
		return queue.iterator();
	}

	@Override
	public T getLast () {
		return queue.getLast();
	}

	@Override
	public List<T> toList () {
		return queue.toList();
	}

	@Override
	public boolean isEmpty () {
		return queue.isEmpty();
	}

	@Override
	public void print (String tag) {
		queue.print(tag);
	}

	@Override
	public void print (String tag, int from_index, int to_index) {
		queue.print(tag, from_index, to_index);
	}

	@Override
	public boolean beginsWith (Collection<T> steps) {
		return queue.beginsWith(steps);
	}

	@Override
	public void clear () {
		queue.clear();
	}

}
