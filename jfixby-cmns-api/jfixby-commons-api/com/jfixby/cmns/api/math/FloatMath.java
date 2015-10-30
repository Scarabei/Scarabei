package com.jfixby.cmns.api.math;

import com.jfixby.cmns.api.components.ComponentInstaller;

public class FloatMath {

	static private ComponentInstaller<FloatMathComponent> componentInstaller = new ComponentInstaller<FloatMathComponent>(
			"FloatMath");

	public static final void installComponent(
			FloatMathComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final FloatMathComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final FloatMathComponent component() {
		return componentInstaller.getComponent();
	}

	public static double sqrt(double x) {
		return invoke().sqrt(x);
	}

	public static long floorDown(double x) {
		return invoke().floorDown(x);
	}

	public static boolean isWithinEpsilon(double x) {
		return invoke().isWithinEpsilon(x);
	}

	public static boolean isIntegerInDoubleEpsilonNeighbourhood(double d) {
		return invoke().isIntegerInDoubleEpsilonNeighbourhood(d);
	}

	public static boolean isIntegerInFloatEpsilonNeighbourhood(double d) {
		return invoke().isIntegerInFloatEpsilonNeighbourhood(d);
	}

	public static boolean isInteger(double d) {
		return invoke().isInteger(d);
	}

	public static boolean isFloatInteger(double d) {
		return invoke().isFloatInteger(d);
	}

	public static double pow(double x, double pow) {
		return invoke().pow(x, pow);
	}

	public static long integerPartOf(double d) {
		return invoke().integerPartOf(d);
	}

	public static double fractionalPartOf(double d) {
		return invoke().fractionalPartOf(d);
	}

	public static double ONE() {
		return invoke().ONE();
	}

	public static long floorUp(double d) {
		return invoke().floorUp(d);
	}

	public static double DOUBLE_EPSILON() {
		return invoke().DOUBLE_EPSILON();
	}

	public static double EPSILON() {
		return invoke().EPSILON();
	}

	public static double FLOAT_EPSILON() {
		return invoke().FLOAT_EPSILON();
	}

	public static double abs(double f) {
		return invoke().abs(f);
	}

	public static int INDEX(boolean x) {
		return invoke().INDEX(x);
	}

	public static double distance(double xa, double ya, double xb, double yb) {
		return invoke().distance(xa, ya, xb, yb);
	}

	public static double min(double a, double b) {
		return invoke().min(a, b);
	}

	public static double max(double a, double b) {
		return invoke().max(a, b);
	}

	public static double toDegrees(double radian) {
		return invoke().toDegrees(radian);
	}

	public static double toRadians(double d) {
		return invoke().toRadians(d);
	}

	public static double sin(double angle) {
		return invoke().sin(angle);
	}

	public static double cos(double angle) {
		return invoke().cos(angle);
	}

	public static double tan(double angle) {
		return invoke().tan(angle);
	}

	public static double norm(double x, double y) {
		return invoke().norm(x, y);
	}

	public static double VAL_0() {
		return invoke().VAL_0();
	}

	public static double VAL_PI_d_2() {
		return invoke().VAL_PI_d_2();
	}

	public static double PI() {
		return invoke().PI();
	}

	public static double VAL_k2_d_2() {
		return invoke().VAL_k2_d_2();
	}

	public static double aOmegaAB(double nX) {
		return invoke().aOmegaAB(nX);
	}

	public static double VAL_3PI_d_2() {
		return invoke().VAL_3PI_d_2();
	}

	public static double VAL_mk2_d_2() {
		return invoke().VAL_mk2_d_2();
	}

	public static double VAL_mPI_d_2() {
		return invoke().VAL_mPI_d_2();
	}

	public static boolean isEpsilonEqualFloat(double double1, double double2) {
		return invoke().isEpsilonEqualFloat(double1, double2);
	}

	public static double VAL_2PI() {
		return invoke().VAL_2PI();
	}

	public static boolean isEpsilonEqual(double double1, double double2) {
		return invoke().isEpsilonEqual(double1, double2);
	}

	public static boolean isEpsilonEqualDouble(double double1, double double2) {
		return invoke().isEpsilonEqualDouble(double1, double2);
	}

	public static double limit(double left_border, double value,
			double right_border) {
		return invoke().limit(left_border, value, right_border);
	}

	public static double DOUBLE_EPSILON(int number_of_safe_operations) {
		return invoke().DOUBLE_EPSILON(number_of_safe_operations);
	}

	public static long round(double float_value) {
		return invoke().round(float_value);
	}
}
