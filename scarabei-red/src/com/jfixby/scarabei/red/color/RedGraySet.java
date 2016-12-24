
package com.jfixby.scarabei.red.color;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.color.GraySet;
import com.jfixby.scarabei.api.java.FloatValue;

public class RedGraySet implements GraySet {

	@Override
	public float findClosest (final float valueAt) {
		float closest_value = Float.MAX_VALUE;
		float closest_distance = Float.MAX_VALUE;
		float distance = 0;
		for (int i = 0; i < this.palette.size(); i++) {
			final FloatValue value = this.palette.getElementAt(i);
			distance = abs(valueAt - value.value);
			if (distance < closest_distance) {
				closest_value = (float)value.value;
				closest_distance = distance;
				if (closest_distance == 0) {
					return closest_value;
				}
			}
		}
		return closest_value;
	}

	private static final float abs (double d) {
		if (d < 0) {
			return (float)-d;
		}
		return (float)d;
	}

	final List<FloatValue> palette = Collections.newList();

	@Override
	public GraySet addAll (float... array) {
		for (int i = 0; i < array.length; i++) {
			this.add(array[i]);
		}
		return this;
	}

	@Override
	public GraySet add (float value) {
		FloatValue add = new FloatValue();
		add.value = value;
		if (!this.palette.contains(add)) {
			this.palette.add(add);
		}
		return this;
	}

	@Override
	public void print (String string) {
		this.palette.print(string);
	}

	@Override
	public void sort () {
		palette.sort();
	}

	@Override
	public int size () {
		return palette.size();
	}

	@Override
	public float getValue (int index) {
		return (float)this.palette.getElementAt(index).value;
	}

	@Override
	public int indexOf (float exactValue) {
		for (int i = 0; i < this.palette.size(); i++) {
			if (this.palette.getElementAt(i).value == exactValue) {
				return i;
			}
		}
		return -1;
	}

}
