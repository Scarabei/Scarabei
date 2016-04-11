
package com.jfixby.red.collections;

import java.util.Comparator;
import java.util.Iterator;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.CollectionFilter;
import com.jfixby.cmns.api.collections.CollectionScanner;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Histogramm;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;

public class RedHistogramm<T> implements Histogramm<T> {
	final Map<T, RedHistogrammValue> storage = Collections.newMap();
	long max = 0;
	final private CollectionScanner<T> max_scanner = new CollectionScanner<T>() {
		@Override
		public void scanElement (T element, int index) {
			max = max(getNumberAt(index), max);
		}
	};

	@Override
	public void add (T value) {
		RedHistogrammValue num = storage.get(value);
		if (num == null) {
			num = new RedHistogrammValue(this);
			storage.put(value, num);
		}
		num.value = num.value + 1;
		max = max(max, num.value);
	}

	final static private long max (long a, long b) {
		if (a > b) {
			return a;
		}
		return b;
	}

	@Override
	public long getNumberOf (T value) {
		RedHistogrammValue num = storage.get(value);
		if (num == null) {
			return 0;
		}
		return num.value;
	}

	@Override
	public long getMax () {
		return max;
	}

	@Override
	public void print (String tag) {
		storage.print(tag);
	}

	@Override
	public void sortValues () {
		storage.sortKeys();
	}

	@Override
	public void addIf (T value, boolean condition) {
		if (condition) {
			this.add(value);
		}
	}

	@Override
	public long getNumberAt (long index) {
		return storage.getValueAt(index).value;
	}

	@Override
	public int size () {
		return storage.size();
	}

	@Override
	public T getValueAt (long index) {
		return this.storage.getKeyAt(index);
	}

	@Override
	public void sortNumbers () {
		final Comparator<T> comparator = new Comparator<T>() {
			@Override
			public int compare (T o1, T o2) {
				return -Long.compare(storage.get(o1).value, storage.get(o2).value);
			}
		};
		storage.sortKeys(comparator);
	}

	@Override
	public boolean contains (Object element) {
		return this.storage.keys().contains(element);
	}

	@Override
	public java.util.List<T> toJavaList () {
		return this.storage.keys().toJavaList();
	}

	@Override
	public T getElementAt (long i) {
		return this.storage.getKeyAt(i);
	}

	@Override
	public Iterator<T> iterator () {
		return this.storage.keys().iterator();
	}

	@Override
	public T getLast () {
		return this.storage.keys().getLast();
	}

	@Override
	public com.jfixby.cmns.api.collections.List<T> toList () {
		return this.storage.keys().toList();
	}

	@Override
	public boolean isEmpty () {
		return this.storage.size() == 0;
	}

	@Override
	public void print (String tag, int from_index, int to_index) {
		this.storage.keys().print(tag, from_index, to_index);
	}

	@Override
	public boolean beginsWith (Collection<T> steps) {
		return this.storage.keys().beginsWith(steps);
	}

	@Override
	public List<T> filter (CollectionFilter<? super T> filter) {
		return this.storage.keys().filter(filter);
	}

	@Override
	public void cutToSize (int max_size) {
		this.storage.cutToSize(max_size);
		this.getMaxAgain();
	}

	private void getMaxAgain () {
		this.max = 0;
		Collections.scanCollection(this, max_scanner);
	}

	public float presence (final long value) {
		return value * 1f / this.max;
	}

}
