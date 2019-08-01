
package com.jfixby.scarabei.api.collections;

public interface Histogramm<T> extends Collection<T>, Mapping<T, Long> {

	void add (T value);

	long getNumberOf (T value);

	long getMax ();

	void sortValues ();

	void addIf (T value, boolean condition);

	long getNumberAt (long index);

	void sortNumbers ();

	void cutToSize (int index_max_size);

	void addAll (Iterable<T> elements);

	long reset (T item);

}
