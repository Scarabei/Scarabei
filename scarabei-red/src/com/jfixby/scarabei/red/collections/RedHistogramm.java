
package com.jfixby.scarabei.red.collections;

import java.util.Comparator;
import java.util.Iterator;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.CollectionConverter;
import com.jfixby.scarabei.api.collections.CollectionFilter;
import com.jfixby.scarabei.api.collections.CollectionScanner;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Histogramm;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.math.IntegerMath;

public class RedHistogramm<T> implements Histogramm<T> {
	final Map<T, RedHistogrammValue> storage = Collections.newMap();
	long max = 0;
	final private CollectionScanner<T> max_scanner = new CollectionScanner<T>() {
		@Override
		public void scanElement (final T element, final long index) {
			RedHistogramm.this.max = max(RedHistogramm.this.getNumberAt(index), RedHistogramm.this.max);
		}
	};

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
	public void add (final T value) {
		RedHistogrammValue num = this.storage.get(value);
		if (num == null) {
			num = new RedHistogrammValue(this);
			this.storage.put(value, num);
		}
		num.value = num.value + 1;
		this.max = max(this.max, num.value);
	}

	final static private long max (final long a, final long b) {
		if (a > b) {
			return a;
		}
		return b;
	}

	@Override
	public long getNumberOf (final T value) {
		final RedHistogrammValue num = this.storage.get(value);
		if (num == null) {
			return 0;
		}
		return num.value;
	}

	@Override
	public long getMax () {
		return this.max;
	}

	@Override
	public void sortValues () {
		this.storage.sortKeys();
	}

	@Override
	public void addIf (final T value, final boolean condition) {
		if (condition) {
			this.add(value);
		}
	}

	@Override
	public long getNumberAt (final long index) {
		return this.storage.getValueAt(index).value;
	}

	@Override
	public int size () {
		return this.storage.size();
	}

	@Override
	public T getKeyAt (final long index) {
		return this.storage.getKeyAt(index);
	}

	@Override
	public void sortNumbers () {
		final Comparator<T> comparator = new Comparator<T>() {
			@Override
			public int compare (final T o1, final T o2) {
				return -IntegerMath.compare(RedHistogramm.this.storage.get(o1).value, RedHistogramm.this.storage.get(o2).value);
			}
		};
		this.storage.sortKeys(comparator);
	}

	@Override
	public boolean contains (final Object element) {
		return this.storage.keys().contains(element);
	}

	@Override
	public java.util.List<T> toJavaList () {
		return this.storage.keys().toJavaList();
	}

	@Override
	public T getElementAt (final long i) {
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
	public int indexOf (final Object element) {
		return this.storage.keys().indexOf(element);
	}

	@Override
	public com.jfixby.scarabei.api.collections.List<T> toList () {
		return this.storage.keys().toList();
	}

	@Override
	public boolean isEmpty () {
		return this.storage.size() == 0;
	}

	@Override
	public boolean beginsWith (final Collection<T> steps) {
		return this.storage.keys().beginsWith(steps);
	}

	@Override
	public List<T> filter (final CollectionFilter<? super T> filter) {
		return this.storage.keys().filter(filter);
	}

	@Override
	public void cutToSize (final int max_size) {
		this.storage.cutToSize(max_size);
		this.getMaxAgain();
	}

	private void getMaxAgain () {
		this.max = 0;
		Collections.scanCollection(this, this.max_scanner);
	}

	public float presence (final long value) {
		return value * 1f / this.max;
	}

	@Override
	public void addAll (final Iterable<T> elements) {
		for (final T t : elements) {
			this.add(t);
		}
	}

	@Override
	public boolean containsKey (final Object key) {
		return this.storage.containsKey(key);
	}

	@Override
	public Long getValueAt (final long i) {
		return this.storage.getValueAt(i).value;
	}

	@Override
	public Iterator<T> keysIterator () {
		return this.storage.keysIterator();
	}

	@Override
	public Collection<Long> values () {
		final List<Long> longs = Collections.newList();
		Collections.convertCollection(this.storage.values(), longs, new CollectionConverter<RedHistogrammValue, Long>() {
			@Override
			public Long convert (final RedHistogrammValue e) {
				return e.value;
			}
		});
		return longs;
	}

	@Override
	public Collection<T> keys () {
		return this.storage.keys();
	}

	@Override
	public java.util.Map<T, Long> toJavaMap () {
		final java.util.LinkedHashMap<T, Long> result = new java.util.LinkedHashMap<T, Long>();
		for (final T k : this.keys()) {
			result.put(k, this.get(k));
		}
		return result;
	}

	@Override
	public Long get (final Object key) {
		return this.storage.get(key).value;
	}

	@Override
	public long reset (final T item) {
		final RedHistogrammValue e = this.storage.remove(item);
		if (e != null) {
			return e.value;
		}
		return 0;
	}

}
