
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.geometry.Circle;
import com.jfixby.scarabei.api.geometry.Vertex;
import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.FloatMath;

public class RedCircle extends VertexMaster implements Circle {

	private final RedVertex point;
	private final RedVertex center;
	double radius = 0;

	RedCircle () {
		this.point = new RedVertex(this);
		this.center = new RedVertex(this);
	}

	public RedCircle (final Circle other) {
		this();

		this.setRadius(other.getRadius());
		Err.throwNotImplementedYet();
	}

	@Override
	public double getRadius () {
		return this.radius;
	}

	@Override
	public void setRadius (final double radius) {
		this.radius = radius;
	}

	@Override
	public Vertex getVertexAt (final Angle direction) {
		return this.getVertexAt(direction.toRadians());
	}

	@Override
	public Vertex getVertexAt (final double direction_radians) {
		this.point.relative().setXY(FloatMath.cos(direction_radians), FloatMath.sin(direction_radians));
		this.point.relative().scaleXY(this.radius);
		return this.point;
	}

	@Override
	public void setXY (final double x, final double y) {
		this.center.relative().setXY(x, y);
	}

	@Override
	public void setRotation (final double rotation) {
		Err.throwNotImplementedYet();
	}

	@Override
	public double getPositionX () {
		return this.center.transformed().getX();
	}

	@Override
	public double getPositionY () {
		return this.center.transformed().getY();
	}

	@Override
	public void setPosition (final double x, final double y) {
		this.center.relative().setXY(x, y);
	}

	@Override
	public Vertex getCenter () {
		return this.center;
	}

}
