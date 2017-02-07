
package com.jfixby.scarabei.api.floatn;

public interface ReadOnlyFloat2
{
	public double norm ();

	double getX ();

	double getY ();

	boolean isInEpsilonDistance (ReadOnlyFloat2 other);

	boolean isInEpsilonDistanceOfZero ();

	public double distanceTo (ReadOnlyFloat2 other);

}
