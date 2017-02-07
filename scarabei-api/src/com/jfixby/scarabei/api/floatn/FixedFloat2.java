
package com.jfixby.scarabei.api.floatn;

import com.jfixby.scarabei.api.geometry.LinearCombinable;

public interface FixedFloat2 extends LinearCombinable<FixedFloat2, Float2> {
	public double norm ();

	double getX ();

	double getY ();

	boolean isInEpsilonDistance (FixedFloat2 other);

	boolean isInEpsilonDistanceOfZero ();

	public double distanceTo (FixedFloat2 other);

}
