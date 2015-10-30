package com.jfixby.cmns.api.math;

import com.jfixby.cmns.api.components.ComponentInstaller;

public class IntegerMath {

	static private ComponentInstaller<IntegerMathComponent> componentInstaller = new ComponentInstaller<IntegerMathComponent>(
			"IntegerMath");

	public static final void installComponent(
			IntegerMathComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final IntegerMathComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final IntegerMathComponent component() {
		return componentInstaller.getComponent();
	}

	public static long limit(long left_border, long value, long right_border) {
		return invoke().limit(left_border, value, right_border);
	}

	public static long ZtoN(long z_number) {
		return invoke().ZtoN(z_number);
	}

	public static long NtoZ(long n_number) {
		return invoke().NtoZ(n_number);
	}

	public static long ZxZtoN(long z_x, long z_y) {
		return invoke().ZxZtoN(z_x, z_y);
	}

	public static long NtoZxZgetX(long number) {
		return invoke().NtoZxZgetX(number);
	}

	public static long NtoZxZgetY(long number) {
		return invoke().NtoZxZgetY(number);
	}

	public static long NxNtoN(long n_x, long n_y) {
		return invoke().NxNtoN(n_x, n_y);
	}

	public static long NtoNxNgetY(long number_xy) {
		return invoke().NtoNxNgetY(number_xy);
	}

	public static long NtoNxNgetX(long number_xy) {
		return invoke().NtoNxNgetX(number_xy);
	}

	public static long min(long a, long b) {
		return invoke().min(a, b);
	}

	public static long max(long a, long b) {
		return invoke().max(a, b);
	}

	public static int index(boolean b) {
		return invoke().index(b);
	}

	public static long power(long a, long n) {
		return invoke().power(a, n);
	}

	public static Int2 newInt2(long cell_x, long cell_y) {
		return invoke().newInt2(cell_x, cell_y);
	}

	public static Int2 newInt2() {
		return invoke().newInt2();
	}

	public static Int2 newInt2(Int2 original, long offset_x, long offset_y) {
		return invoke().newInt2(original, offset_x, offset_y);
	}

	public static long abs(long value) {
		return invoke().abs(value);
	}

}
