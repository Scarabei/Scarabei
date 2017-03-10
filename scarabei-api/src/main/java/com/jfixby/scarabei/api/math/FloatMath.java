
package com.jfixby.scarabei.api.math;

import com.jfixby.scarabei.api.ComponentInstaller;

public class FloatMath {

	static private ComponentInstaller<FloatMathComponent> componentInstaller = new ComponentInstaller<FloatMathComponent>(
		"FloatMath");

	public static final void installComponent (final FloatMathComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final FloatMathComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final FloatMathComponent component () {
		return componentInstaller.getComponent();
	}

	public static double sqrt (final double x) {
		return invoke().sqrt(x);
	}

	public static long floorDown (final double x) {
		return invoke().floorDown(x);
	}

	public static boolean isWithinEpsilon (final double x) {
		return invoke().isWithinEpsilon(x);
	}

	public static boolean isIntegerInDoubleEpsilonNeighbourhood (final double d) {
		return invoke().isIntegerInDoubleEpsilonNeighbourhood(d);
	}

	public static boolean isIntegerInFloatEpsilonNeighbourhood (final double d) {
		return invoke().isIntegerInFloatEpsilonNeighbourhood(d);
	}

	public static boolean isInteger (final double d) {
		return invoke().isInteger(d);
	}

	public static boolean isFloatInteger (final double d) {
		return invoke().isFloatInteger(d);
	}

	public static double power (final double x, final double pow) {
		return invoke().power(x, pow);
	}

	public static long integerPartOf (final double d) {
		return invoke().integerPartOf(d);
	}

	public static double fractionalPartOf (final double d) {
		return invoke().fractionalPartOf(d);
	}

	public static double ONE () {
		return invoke().ONE();
	}

	public static long floorUp (final double d) {
		return invoke().floorUp(d);
	}

	public static double DOUBLE_EPSILON () {
		return invoke().DOUBLE_EPSILON();
	}

	public static double EPSILON () {
		return invoke().EPSILON();
	}

	public static double FLOAT_EPSILON () {
		return invoke().FLOAT_EPSILON();
	}

	public static double abs (final double f) {
		return invoke().abs(f);
	}

	public static int INDEX (final boolean x) {
		return invoke().INDEX(x);
	}

	public static double distance (final double x1, final double y1, final double x2, final double y2) {
		return invoke().distance(x1, y1, x2, y2);
	}

	public static double min (final double a, final double b) {
		return invoke().min(a, b);
	}

	public static double max (final double a, final double b) {
		return invoke().max(a, b);
	}

	public static double toDegrees (final double radian) {
		return invoke().toDegrees(radian);
	}

	public static double toRadians (final double degrees) {
		return invoke().toRadians(degrees);
	}

	public static double sin (final double angle) {
		return invoke().sin(angle);
	}

	public static double cos (final double angle) {
		return invoke().cos(angle);
	}

	public static double tan (final double angle) {
		return invoke().tan(angle);
	}

	public static double norm (final double x, final double y) {
		return invoke().norm(x, y);
	}

	public static double VAL_0 () {
		return invoke().VAL_0();
	}

	public static double VAL_PI_d_2 () {
		return invoke().VAL_PI_d_2();
	}

	public static double PI () {
		return invoke().PI();
	}

	public static double VAL_k2_d_2 () {
		return invoke().VAL_k2_d_2();
	}

	public static double aOmegaAB (final double nX) {
		return invoke().aOmegaAB(nX);
	}

	public static double VAL_3PI_d_2 () {
		return invoke().VAL_3PI_d_2();
	}

	public static double VAL_mk2_d_2 () {
		return invoke().VAL_mk2_d_2();
	}

	public static double VAL_mPI_d_2 () {
		return invoke().VAL_mPI_d_2();
	}

	public static boolean isEpsilonEqualFloat (final double double1, final double double2) {
		return invoke().isEpsilonEqualFloat(double1, double2);
	}

	public static double VAL_2PI () {
		return invoke().VAL_2PI();
	}

	public static boolean isEpsilonEqual (final double double1, final double double2) {
		return invoke().isEpsilonEqual(double1, double2);
	}

	public static boolean isEpsilonEqualDouble (final double double1, final double double2) {
		return invoke().isEpsilonEqualDouble(double1, double2);
	}

	public static double limit (final double left_border, final double value, final double right_border) {
		return invoke().limit(left_border, value, right_border);
	}

	public static float limit (final float left_border, final float value, final float right_border) {
		return invoke().limit(left_border, value, right_border);
	}

	public static double DOUBLE_EPSILON (final int number_of_safe_operations) {
		return invoke().DOUBLE_EPSILON(number_of_safe_operations);
	}

	public static long round (final double float_value) {
		return invoke().round(float_value);
	}

	public static double log (final double base, final double exp_value) {
		return invoke().log(base, exp_value);
	}

	public static double roundToDigit (final double raw_value, final int index_after_point) {
		return invoke().roundToDigit(raw_value, index_after_point);
	}

	public static boolean isIntegerInEpsilonNeighbourhood (final double value, final double epsilon) {
		return invoke().isIntegerInEpsilonNeighbourhood(value, epsilon);
	}

	public static Average newAverage (final int max_num_of_values) {
		return invoke().newAverage(max_num_of_values);
	}
}
