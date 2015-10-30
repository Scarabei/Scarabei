package com.jfixby.cmns.api.angles;

import com.jfixby.cmns.api.math.Angle;
import com.jfixby.cmns.api.math.CustomAngle;

public interface AnglesComponent {

	CustomAngle newAngle();

	CustomAngle newAngle(double a);

	CustomAngle newAngle(Angle a);

	CustomAngle ZERO();

	CustomAngle PI();

	CustomAngle PI_twice();

	CustomAngle subtract(CustomAngle a, CustomAngle b,
			CustomAngle a_minus_b_result);

	CustomAngle g45();

	CustomAngle g60();

	CustomAngle g30();

	CustomAngle g90();

	CustomAngle g180();

	void parametrize(Angle a, double progress_from_A_to_B, Angle b,
			CustomAngle result);

}
