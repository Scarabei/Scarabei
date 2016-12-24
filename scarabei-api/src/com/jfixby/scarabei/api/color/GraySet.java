
package com.jfixby.scarabei.api.color;

public interface GraySet {

	float findClosest (float valueAt);

	GraySet addAll (float... array);

	GraySet add (float value);

	void print (String string);

	void sort ();

	int size ();

	float getValue (int index);

	int indexOf (float exactValue);

}
