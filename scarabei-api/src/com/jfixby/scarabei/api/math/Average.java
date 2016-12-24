
package com.jfixby.scarabei.api.math;

public interface Average {

	void addValue (double time);

	int size ();

	double getAverage ();

	double getLast ();

}
