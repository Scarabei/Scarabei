
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.math.FloatMath;

public class RedPoint implements Float2, ReadOnlyFloat2 {

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.x);
		result = prime * result + (int)(temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.y);
		result = prime * result + (int)(temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedPoint other = (RedPoint)obj;
		if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString () {
		return "(" + this.x + ", " + this.y + ")";
	}

	double x;
	double y;

	public RedPoint () {
	}

	public RedPoint (final ReadOnlyFloat2 dot) {
		this();
		this.set(dot);
	}

	public RedPoint (final double x, final double y) {
		this.setXY(x, y);
	}

	@Override
	public Float2 setXY (final double x, final double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public Float2 setX (final double x) {
		this.x = x;
		return this;
	}

	@Override
	public Float2 setY (final double y) {
		this.y = y;
		return this;
	}

	@Override
	public double getX () {
		return this.x;
	}

	@Override
	public double getY () {
		return this.y;
	}

	@Override
	public Float2 set (final ReadOnlyFloat2 other) {
		this.setXY(other.getX(), other.getY());
		return this;
	}

	@Override
	public Float2 setXY () {
		this.setXY(0, 0);
		return this;
	}

	@Override
	public Float2 add (final ReadOnlyFloat2 offset) {
		this.setXY(this.x + offset.getX(), this.y + offset.getY());
		return this;
	}

	@Override
	public Float2 addX (final double delta) {
		this.setXY(this.x + delta, this.y);
		return this;
	}

	@Override
	public Float2 addY (final double delta) {
		this.setXY(this.x, this.y + delta);
		return this;
	}

	@Override
	public Float2 add (final double deltaX, final double deltaY) {
		this.setXY(this.x + deltaX, this.y + deltaY);
		return this;
	}

	@Override
	public boolean isInEpsilonDistance (final ReadOnlyFloat2 other) {
		return Geometry.isInEpsilonDistance(this, other);
	}

	@Override
	public boolean isInEpsilonDistanceOfZero () {
		return Geometry.isInEpsilonDistanceOfZero(this);
	}

	@Override
	public double norm () {
		return FloatMath.distance(this.x, this.y, 0, 0);
	}

	@Override
	public double distanceTo (final ReadOnlyFloat2 other) {
		return Geometry.distance(this, other);
	}

	@Override
	public Float2 scaleXY (final double factor) {
		return this.setXY(this.getX() * factor, factor * this.getY());
	}

	@Override
	public Float2 scaleXY (final double x, final double y) {
		this.x = this.x * x;
		this.y = this.y * y;
		return this;
	}

	@Override
	public Float2 scaleXY (final Float2 other) {
		return this.scaleXY(other.getX(), other.getY());
	}

	@Override
	public double product (final Float2 other) {
		return this.x * other.getX() + this.y * other.getY();
	}

	@Override
	public RedPoint setLinearSum (final ReadOnlyFloat2 a, final double alpha, final ReadOnlyFloat2 b, final double betta) {
		this.x = a.getX() * alpha + b.getX() * betta;
		this.y = a.getY() * alpha + b.getY() * betta;
		return this;
	}

	@Override
	public void subtract (final ReadOnlyFloat2 position) {
		this.x = this.x - position.getX();
		this.y = this.y - position.getY();
	}

// @Override
// public Float2 getLinearSum (final double alpha, final FixedFloat2 other, final double betta, final Float2 output) {
// return output.setLinearSum(this, alpha, other, betta);
// }

}
