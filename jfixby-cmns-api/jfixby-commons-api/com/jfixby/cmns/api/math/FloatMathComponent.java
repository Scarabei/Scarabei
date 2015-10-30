package com.jfixby.cmns.api.math;

public interface FloatMathComponent {

	public double sqrt(double x);

	public long floorDown(double x);

	public boolean isWithinEpsilon(double x);

	public boolean isIntegerInDoubleEpsilonNeighbourhood(double d);

	public boolean isIntegerInFloatEpsilonNeighbourhood(double d);

	public boolean isInteger(double d);

	public boolean isFloatInteger(double d);

	public double pow(double x, double pow);

	public long integerPartOf(double d);

	public double fractionalPartOf(double d);

	public long floorUp(double d);

	public double abs(double f);

	public boolean isEpsilonEqualFloat(double double1, double double2);

	public double aOmegaAB(double nX);

	public int INDEX(boolean x);

	public double distance(double xa, double ya, double xb, double yb);

	public double min(double a, double b);

	public double max(double a, double b);

	public double toRadians(double d);

	public double toDegrees(double radian);

	public double sin(double angle);

	public double cos(double angle);

	public double tan(double angle);

	public double norm(double x, double y);

	public double VAL_PI_d_2();

	public double VAL_0();

	public double PI();

	public double VAL_k2_d_2();

	public double VAL_3PI_d_2();

	public double VAL_mk2_d_2();

	public double VAL_mPI_d_2();

	public double ONE();

	public double DOUBLE_EPSILON();

	public double FLOAT_EPSILON();

	public double EPSILON();

	public double VAL_2PI();

	public boolean isEpsilonEqual(double double1, double double2);

	public boolean isEpsilonEqualDouble(double double1, double double2);

	public double limit(double left_border, double value, double right_border);

	public double DOUBLE_EPSILON(int number_of_safe_operations);

	public long round(double float_value);

}
