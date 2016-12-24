
package com.jfixby.scarabei.api.angles;

import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.CustomAngle;

public interface AnglesComponent {

	CustomAngle newAngle ();

	CustomAngle newAngle (double a);

	CustomAngle newAngle (Angle a);

	CustomAngle ZERO ();

	CustomAngle PI ();

	CustomAngle PI_twice ();

	CustomAngle subtract (CustomAngle a, CustomAngle b, CustomAngle a_minus_b_result);

	CustomAngle g45 ();

	CustomAngle g60 ();

	CustomAngle g30 ();

	CustomAngle g90 ();

	CustomAngle g180 ();

	void parametrize (Angle a, double progress_from_A_to_B, Angle b, CustomAngle result);

	CustomAngle g270 ();

}
