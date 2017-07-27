
package com.jfixby.scarabei.red.arrays;

import com.jfixby.scarabei.api.arrays.ArraysComponent;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Set;

public class RedArrays implements ArraysComponent {

	@Override
	public List<Float> newFloatsList (float[] floats) {
		List<Float> result = Collections.newList();
		for (int i = 0; i < floats.length; i++) {
			Float float_val = floats[i];
			result.add(float_val);
		}
		return result;
	}

	@Override
	public List<Integer> newIntsList (int[] ints) {
		List<Integer> result = Collections.newList();
		for (int i = 0; i < ints.length; i++) {
			Integer float_val = ints[i];
			result.add(float_val);
		}
		return result;
	}

	@Override
	public <T> List<T> newList (T[] array) {
		List<T> result = Collections.newList();
		for (int i = 0; i < array.length; i++) {
			T float_val = array[i];
			result.add(float_val);
		}
		return result;
	}

	@Override
	public <T> Set<T> newSet (T[] array) {
		Set<T> result = Collections.newSet();
		for (int i = 0; i < array.length; i++) {
			T float_val = array[i];
			result.add(float_val);
		}
		return result;
	}

}
