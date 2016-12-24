
package com.jfixby.scarabei.api.math;

import com.jfixby.scarabei.api.ComponentInstaller;

public class IntegerMath {

	static private ComponentInstaller<IntegerMathComponent> componentInstaller = new ComponentInstaller<IntegerMathComponent>(
		"IntegerMath");

	public static final void installComponent (final IntegerMathComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final IntegerMathComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final IntegerMathComponent component () {
		return componentInstaller.getComponent();
	}

	public static long limit (final long left_border, final long value, final long right_border) {
		return invoke().limit(left_border, value, right_border);
	}

	public static long ZtoN (final long z_number) {
		return invoke().ZtoN(z_number);
	}

	public static long NtoZ (final long n_number) {
		return invoke().NtoZ(n_number);
	}

	public static long ZxZtoN (final long z_x, final long z_y) {
		return invoke().ZxZtoN(z_x, z_y);
	}

	public static long NtoZxZgetX (final long number) {
		return invoke().NtoZxZgetX(number);
	}

	public static long NtoZxZgetY (final long number) {
		return invoke().NtoZxZgetY(number);
	}

	public static long NxNtoN (final long n_x, final long n_y) {
		return invoke().NxNtoN(n_x, n_y);
	}

	public static long NtoNxNgetY (final long number_xy) {
		return invoke().NtoNxNgetY(number_xy);
	}

	public static long NtoNxNgetX (final long number_xy) {
		return invoke().NtoNxNgetX(number_xy);
	}

	public static long min (final long a, final long b) {
		return invoke().min(a, b);
	}

	public static long max (final long a, final long b) {
		return invoke().max(a, b);
	}

	public static int index (final boolean b) {
		return invoke().index(b);
	}

	public static long power (final long a, final int n) {
		return invoke().power(a, n);
	}

	public static Int2 newInt2 (final long cell_x, final long cell_y) {
		return invoke().newInt2(cell_x, cell_y);
	}

	public static Int2 newInt2 () {
		return invoke().newInt2();
	}

	public static Int2 newInt2 (final Int2 original, final long offset_x, final long offset_y) {
		return invoke().newInt2(original, offset_x, offset_y);
	}

	public static long abs (final long value) {
		return invoke().abs(value);
	}

	public static boolean isPowerOfTwo (final int x) {
		return invoke().isPowerOfTwo(x);
	}

	public static long rightPowerOfTwo (final long value) {
		return invoke().rightPowerOfTwo(value);
	}

	public static int compare (final long a, final long b) {
		return invoke().compare(a, b);
	}

}
