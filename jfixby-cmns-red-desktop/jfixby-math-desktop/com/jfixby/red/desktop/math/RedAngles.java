package com.jfixby.red.desktop.math;

import com.jfixby.cmns.api.angles.AnglesComponent;
import com.jfixby.cmns.api.math.Angle;
import com.jfixby.cmns.api.math.CustomAngle;

public class RedAngles implements AnglesComponent {

	@Override
	public CustomAngle newAngle() {
		return new RedAngle();
	}

	@Override
	public CustomAngle ZERO() {
		return new RedAngle(0);
	}

	@Override
	public CustomAngle PI() {
		return new RedAngle(Math.PI);
	}

	@Override
	public CustomAngle newAngle(double a) {
		return new RedAngle(a);
	}

	@Override
	public CustomAngle PI_twice() {
		return new RedAngle(Math.PI * 2d);
	}

	@Override
	public CustomAngle subtract(CustomAngle a, CustomAngle b,
			CustomAngle result_of_a_subtract_b) {
		return RedAngle.subtract(a, b, result_of_a_subtract_b);
	}

	@Override
	public CustomAngle g45() {
		return new RedAngle(Math.PI / 4d);
	}

	@Override
	public CustomAngle g60() {
		return new RedAngle(Math.PI / 3d);
	}

	@Override
	public CustomAngle g30() {
		return new RedAngle(Math.PI / 6d);
	}

	@Override
	public CustomAngle g90() {
		return new RedAngle(Math.PI / 2d);
	}

	@Override
	public CustomAngle g180() {
		return new RedAngle(Math.PI);
	}

	@Override
	public void parametrize(Angle a, double progress_from_A_to_B, Angle b,
			CustomAngle result) {
		result.setValue(a.toRadians() + (b.toRadians() - a.toRadians())
				* progress_from_A_to_B);
	}

	@Override
	public CustomAngle newAngle(Angle a) {
		return this.newAngle().setValue(a);
	}

}
