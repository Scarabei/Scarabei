
package com.jfixby.scarabei.api.math;

public interface FloatMathComponent {

	public double sqrt (double x);

	public long floorDown (double x);

	public boolean isWithinEpsilon (double x);

	public boolean isIntegerInDoubleEpsilonNeighbourhood (double d);

	public boolean isIntegerInFloatEpsilonNeighbourhood (double d);

	public boolean isInteger (double d);

	public boolean isFloatInteger (double d);

	public double power (double x, double pow);

	public long integerPartOf (double d);

	public double fractionalPartOf (double d);

	public long floorUp (double d);

	public double abs (double f);

	public boolean isEpsilonEqualFloat (double double1, double double2);

	public double aOmegaAB (double nX);

	public int INDEX (boolean x);

	public double distance (double x1, double y1, double x2, double y2);

	public double min (double a, double b);

	public double max (double a, double b);

	public double toRadians (double degrees);

	public double toDegrees (double radian);

	public double sin (double angle);

	public double cos (double angle);

	public double tan (double angle);

	public double norm (double x, double y);

	public double VAL_PI_d_2 ();

	public double VAL_0 ();

	public double PI ();

	public double VAL_k2_d_2 ();

	public double VAL_3PI_d_2 ();

	public double VAL_mk2_d_2 ();

	public double VAL_mPI_d_2 ();

	public double ONE ();

	public double DOUBLE_EPSILON ();

	public double FLOAT_EPSILON ();

	public double EPSILON ();

	public double VAL_2PI ();

	public boolean isEpsilonEqual (double double1, double double2);

	public boolean isEpsilonEqualDouble (double double1, double double2);

	public double limit (double left_border, double value, double right_border);

	public float limit (float left_border, float value, float right_border);

	public double DOUBLE_EPSILON (int number_of_safe_operations);

	public long round (double float_value);

	public double log (double base, double exp_value);

	public double roundToDigit (double raw_value, int index_after_point);

	public boolean isIntegerInEpsilonNeighbourhood (double value, double epsilon);

	public Average newAverage (int max_num_of_values);

}
