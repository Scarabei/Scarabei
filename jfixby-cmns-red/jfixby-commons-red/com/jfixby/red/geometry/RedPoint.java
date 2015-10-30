package com.jfixby.red.geometry;

import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.Geometry;

public class RedPoint implements Float2, FixedFloat2 {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RedPoint other = (RedPoint) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	double x;
	double y;

	public RedPoint() {
	}

	public RedPoint(FixedFloat2 dot) {
		this();
		this.set(dot);
	}

	public RedPoint(double x, double y) {
		this.setXY(x, y);
	}

	@Override
	public Float2 setXY(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public Float2 setX(double x) {
		this.x = x;
		return this;
	}

	@Override
	public Float2 setY(double y) {
		this.y = y;
		return this;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public Float2 set(FixedFloat2 other) {
		this.setXY(other.getX(), other.getY());
		return this;
	}

	@Override
	public Float2 setXY() {
		this.setXY(0, 0);
		return this;
	}

	@Override
	public Float2 add(final FixedFloat2 offset) {
		this.setXY(x + offset.getX(), y + offset.getY());
		return this;
	}

	@Override
	public Float2 addX(double delta) {
		this.setXY(x + delta, y);
		return this;
	}

	@Override
	public Float2 addY(double delta) {
		this.setXY(x, y + delta);
		return this;
	}

	@Override
	public Float2 add(double deltaX, double deltaY) {
		this.setXY(x + deltaX, y + deltaY);
		return this;
	}

	@Override
	public boolean isInEpsilonDistance(FixedFloat2 other) {
		return Geometry.isInEpsilonDistance(this, other);
	}

	@Override
	public boolean isInEpsilonDistanceOfZero() {
		return Geometry.isInEpsilonDistanceOfZero(this);
	}

	@Override
	public double distanceTo(FixedFloat2 other) {
		return Geometry.distance(this, other);
	}

	@Override
	public void scaleXY(double factor) {
		this.setXY(this.getX() * factor, factor * this.getY());
	}

	@Override
	public void scaleXY(double x, double y) {
		this.x = this.x * x;
		this.y = this.y * y;
	}

	@Override
	public void scaleXY(Float2 other) {
		this.scaleXY(other.getX(), other.getY());
	}

	@Override
	public double product(Float2 other) {
		return this.x * other.getX() + this.y * other.getY();
	}

}
