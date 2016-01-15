package com.jfixby.cmns.api.math;

public interface Angle {

	double toRadians();

	double toDegrees();

	double getMagnitude();

	public int getSignum();

	public Angle freeze();

	public CustomAngle newAngle();

}
