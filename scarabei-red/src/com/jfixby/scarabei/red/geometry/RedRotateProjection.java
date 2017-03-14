
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.angles.Angles;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.projections.RotateProjection;
import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.CustomAngle;

public class RedRotateProjection implements RotateProjection {
	private final CustomAngle a;

	public RedRotateProjection () {
		this.a = Angles.newAngle();
	}

	double x = 0;
	double y = 0;

	@Override
	public void project (final Float2 point) {
		this.x = point.getX();
		this.y = point.getY();
		final double r = this.a.toRadians();
		point.setX(this.x * Math.cos(r) - Math.sin(r) * this.y);
		point.setY(Math.sin(r) * this.x + this.y * Math.cos(r));

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

	@Override
	public void unProject (final Float2 point) {
		this.x = point.getX();
		this.y = point.getY();
		final double r = -this.a.toRadians();
		point.setX(this.x * Math.cos(r) - Math.sin(r) * this.y);
		point.setY(Math.sin(r) * this.x + this.y * Math.cos(r));
	}

}
