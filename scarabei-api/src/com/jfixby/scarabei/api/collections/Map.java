
package com.jfixby.scarabei.api.collections;

import java.util.Comparator;

public interface Map<K, V> extends Mapping<K, V> {

	void put (K key, V value);

	void clear ();

	V remove (Object key);

	void putAll (Mapping<? extends K, ? extends V> other_map);

	void putJavaMap (java.util.Map<? extends K, ? extends V> java_map);

	void removeAll (Collection<?> keys);

	void sortKeys ();

	void sortKeys (Comparator<? super K> keysComparator);

	EditableCollection<K> cutToSize (int max_size);

	void setDefaultValue (V defaultVal);

}
