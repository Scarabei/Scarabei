
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.angles.Angles;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.Rectangle;
import com.jfixby.scarabei.api.geometry.projections.RotateProjection;
import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.CustomAngle;

public class RedRotateProjection implements RotateProjection {
	private final CustomAngle a;
	private Rectangle rectangle;

	public RedRotateProjection () {
		this.a = Angles.newAngle();
	}

	public RedRotateProjection (final Rectangle rectangle) {
		this.a = null;
		this.rectangle = rectangle;
	}

	double tmpX = 0;
	double tmpY = 0;

	public Angle a () {
		if (this.rectangle == null) {
			return this.a;
		}
		return this.rectangle.getRotation();

	}

	@Override
	public void project (final Float2 point) {
		this.tmpX = point.getX();
		this.tmpY = point.getY();
		final double r = this.a().toRadians();
		point.setX(this.tmpX * Math.cos(r) - Math.sin(r) * this.tmpY);
		point.setY(Math.sin(r) * this.tmpX + this.tmpY * Math.cos(r));

	}

	@Override
	public void setRotation (final Angle rotation) {
		if (this.rectangle == null) {
			this.a.setValue(rotation);
		}
		this.rectangle.setRotation(rotation);
	}

	@Override
	public void setRotation (final double rotation) {
		if (this.rectangle == null) {
			this.a.setValue(rotation);
		}
		this.rectangle.setRotation(rotation);
	}

	@Override
	public Angle getRotation () {
		return this.a();
	}

	@Override
	public String toString () {
		return "Rotate[" + this.a() + "]";
	}

	@Override
	public void unProject (final Float2 point) {
		this.tmpX = point.getX();
		this.tmpY = point.getY();
		final double r = -this.a().toRadians();
		point.setX(this.tmpX * Math.cos(r) - Math.sin(r) * this.tmpY);
		point.setY(Math.sin(r) * this.tmpX + this.tmpY * Math.cos(r));
	}

}
