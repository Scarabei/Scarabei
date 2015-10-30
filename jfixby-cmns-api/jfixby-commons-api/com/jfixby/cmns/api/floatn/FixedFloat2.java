package com.jfixby.cmns.api.floatn;

public interface FixedFloat2 {

	double getX();

	double getY();

	boolean isInEpsilonDistance(FixedFloat2 other);

	boolean isInEpsilonDistanceOfZero();

	public double distanceTo(FixedFloat2 other);

}
