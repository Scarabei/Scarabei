package com.jfixby.red.geometry;

import com.jfixby.cmns.api.geometry.Circle;
import com.jfixby.cmns.api.geometry.Vertex;
import com.jfixby.cmns.api.math.Angle;
import com.jfixby.cmns.api.math.FloatMath;

public class RedCircle extends VertexMaster implements Circle {

	private RedVertex point;
	private RedVertex center;
	double radius = 0;

	RedCircle() {
		point = new RedVertex(this);
		center = new RedVertex(this);
	}

	public RedCircle(Circle other) {
		this();

		this.setRadius(other.getRadius());
		throw new Error();
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public Vertex getVertexAt(Angle direction) {
		return this.getVertexAt(direction.toRadians());
	}

	@Override
	public Vertex getVertexAt(double direction_radians) {
		point.relative().setXY(FloatMath.cos(direction_radians),
				FloatMath.sin(direction_radians));
		point.relative().scaleXY(this.radius);
		return point;
	}

	@Override
	public void setXY(double x, double y) {
		center.relative().setXY(x, y);
	}

	@Override
	public void setRotation(double rotation) {
		this.getTransform().setRotation(rotation);
	}

	@Override
	public double getPositionX() {
		return center.transformed().getX();
	}

	@Override
	public double getPositionY() {
		return center.transformed().getY();
	}

	@Override
	public void setPosition(double x, double y) {
		center.relative().setXY(x, y);
	}

	@Override
	public Vertex getCenter() {
		return center;
	}

}
