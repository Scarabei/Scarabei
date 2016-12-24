
package com.jfixby.scarabei.red.math;

import com.jfixby.scarabei.api.angles.AnglesComponent;
import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.CustomAngle;

public class RedAngles implements AnglesComponent {

	@Override
	public CustomAngle newAngle () {
		return new RedAngle();
	}

	@Override
	public CustomAngle ZERO () {
		return new RedAngle(0);
	}

	@Override
	public CustomAngle PI () {
		return new RedAngle(Math.PI);
	}

	@Override
	public CustomAngle newAngle (final double a) {
		return new RedAngle(a);
	}

	@Override
	public CustomAngle PI_twice () {
		return new RedAngle(Math.PI * 2d);
	}

	@Override
	public CustomAngle subtract (final CustomAngle a, final CustomAngle b, final CustomAngle result_of_a_subtract_b) {
		return RedAngle.subtract(a, b, result_of_a_subtract_b);
	}

	@Override
	public CustomAngle g45 () {
		return new RedAngle(Math.PI / 4d);
	}

	@Override
	public CustomAngle g60 () {
		return new RedAngle(Math.PI / 3d);
	}

	@Override
	public CustomAngle g30 () {
		return new RedAngle(Math.PI / 6d);
	}

	@Override
	public CustomAngle g90 () {
		return new RedAngle(Math.PI / 2d);
	}

	@Override
	public CustomAngle g180 () {
		return new RedAngle(Math.PI);
	}

	@Override
	public CustomAngle g270 () {
		return new RedAngle(Math.PI * 1.5);
	}

	@Override
	public void parametrize (final Angle a, final double progress_from_A_to_B, final Angle b, final CustomAngle result) {
		result.setValue(a.toRadians() + (b.toRadians() - a.toRadians()) * progress_from_A_to_B);
	}

	@Override
	public CustomAngle newAngle (final Angle a) {
		return this.newAngle().setValue(a);
	}

}
