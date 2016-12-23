
package com.jfixby.cmns.api.floatn;

public interface FixedFloat2 {
	public double norm ();

	double getX ();

	double getY ();

	boolean isInEpsilonDistance (FixedFloat2 other);

	boolean isInEpsilonDistanceOfZero ();

	public double distanceTo (FixedFloat2 other);

}
