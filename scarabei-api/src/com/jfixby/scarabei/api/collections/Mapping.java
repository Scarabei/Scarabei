
package com.jfixby.scarabei.api.collections;

import java.util.Iterator;

public interface Mapping<K, V> {

	boolean containsKey (Object key);

	V get (Object key);

	void print (String tag);

	int size ();

	K getKeyAt (long i);

	V getValueAt (long i);

	Iterator<K> keysIterator ();

	Collection<V> values ();

	Collection<K> keys ();

	java.util.Map<K, V> toJavaMap ();

}
