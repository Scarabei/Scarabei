
package com.jfixby.red.geometry;

import com.jfixby.cmns.api.angles.Angles;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.RotateProjection;
import com.jfixby.cmns.api.math.Angle;
import com.jfixby.cmns.api.math.CustomAngle;

public class RedRotateProjection implements RotateProjection {
	private final CustomAngle a;

	public RedRotateProjection () {
		this.a = Angles.newAngle();
	}

	@Override
	public void project (final Float2 point) {

	}

	@Override
	public void setRotation (final Angle rotation) {
		this.a.setValue(rotation);
	}

	@Override
	public void setRotation (final double rotation) {
		this.a.setValue(rotation);
	}

	@Override
	public Angle getRotation () {
		return this.a;
	}

	@Override
	public String toString () {
		return "Rotate[" + this.a + "]";
	}

}
