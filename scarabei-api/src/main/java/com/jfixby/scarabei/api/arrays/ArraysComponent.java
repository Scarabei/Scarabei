
package com.jfixby.scarabei.api.arrays;

import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Set;

public interface ArraysComponent {

	<T> List<T> newList (T[] array);

	<T> Set<T> newSet (T[] array);

	List<Float> newFloatsList (float[] floats);

	List<Integer> newIntsList (int[] ints);

}
