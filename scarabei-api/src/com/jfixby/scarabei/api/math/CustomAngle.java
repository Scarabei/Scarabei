
package com.jfixby.scarabei.api.math;

public interface CustomAngle extends Angle {

	CustomAngle setValue (Angle other);

	CustomAngle setValue (double radians);

	void makePositive ();

}
