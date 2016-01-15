package com.jfixby.cmns.api.arrays;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;

public interface ArraysComponent {

	<T> List<T> newList(T[] array);

	<T> Set<T> newSet(T[] array);

	List<Float> newFloatsList(float[] floats);

	List<Integer> newIntsList(int[] ints);

}
