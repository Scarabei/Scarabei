package com.jfixby.cmns.api.angles;

import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.math.Angle;
import com.jfixby.cmns.api.math.CustomAngle;

public class Angles {

	static private ComponentInstaller<AnglesComponent> componentInstaller = new ComponentInstaller<AnglesComponent>(
			"Angles");

	public static final void installComponent(
			AnglesComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final AnglesComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final AnglesComponent component() {
		return componentInstaller.getComponent();
	}

	public static final CustomAngle newAngle() {
		return invoke().newAngle();
	}

	public static final CustomAngle newAngle(double a) {
		return invoke().newAngle(a);
	}

	public static final CustomAngle newAngle(Angle a) {
		return invoke().newAngle(a);
	}

	public static final CustomAngle ZERO() {
		return invoke().ZERO();
	}

	public static final CustomAngle PI() {
		return invoke().PI();
	}

	public static CustomAngle TWICE_PI() {
		return invoke().PI_twice();
	}

	public static CustomAngle subtract(CustomAngle A, CustomAngle B,
			CustomAngle A_minus_B_result) {
		return invoke().subtract(A, B, A_minus_B_result);
	}

	public static CustomAngle g45() {
		return invoke().g45();
	}

	public static CustomAngle g90() {
		return invoke().g90();
	}

	public static CustomAngle g30() {
		return invoke().g30();
	}

	public static CustomAngle g60() {
		return invoke().g60();
	}

	public static CustomAngle g180() {
		return invoke().g180();
	}

	public static void parametrize(Angle A, double progress_from_A_to_B,
			Angle B, CustomAngle result) {
		invoke().parametrize(A, progress_from_A_to_B, B, result);
	}
}
