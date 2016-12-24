
package com.jfixby.scarabei.api.collections;

public interface Pool<T> {

	T obtain ();

	void free (T instance);

	void freeAll (Collection<T> collection);

}
