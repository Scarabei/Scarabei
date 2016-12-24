
package com.jfixby.scarabei.api.angles;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.CustomAngle;

public class Angles {

	static private ComponentInstaller<AnglesComponent> componentInstaller = new ComponentInstaller<AnglesComponent>("Angles");

	public static final void installComponent (final AnglesComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final AnglesComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final AnglesComponent component () {
		return componentInstaller.getComponent();
	}

	public static final CustomAngle newAngle () {
		return invoke().newAngle();
	}

	public static final CustomAngle newAngle (final double a) {
		return invoke().newAngle(a);
	}

	public static final CustomAngle newAngle (final Angle a) {
		return invoke().newAngle(a);
	}

	public static final CustomAngle ZERO () {
		return invoke().ZERO();
	}

	public static final CustomAngle PI () {
		return invoke().PI();
	}

	public static CustomAngle TWICE_PI () {
		return invoke().PI_twice();
	}

	public static CustomAngle subtract (final CustomAngle A, final CustomAngle B, final CustomAngle A_minus_B_result) {
		return invoke().subtract(A, B, A_minus_B_result);
	}

	public static CustomAngle g45 () {
		return invoke().g45();
	}

	public static CustomAngle g90 () {
		return invoke().g90();
	}

	public static CustomAngle g30 () {
		return invoke().g30();
	}

	public static CustomAngle g60 () {
		return invoke().g60();
	}

	public static CustomAngle g180 () {
		return invoke().g180();
	}

	public static void parametrize (final Angle A, final double progress_from_A_to_B, final Angle B, final CustomAngle result) {
		invoke().parametrize(A, progress_from_A_to_B, B, result);
	}

	public static CustomAngle g270 () {
		return invoke().g270();
	}
}
