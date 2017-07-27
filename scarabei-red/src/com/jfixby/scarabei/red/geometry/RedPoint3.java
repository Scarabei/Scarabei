
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.floatn.Float3;

public class RedPoint3 implements Float3 {

	double x, y, z;

	public RedPoint3 (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public double getX () {
		return x;
	}

	@Override
	public double getY () {
		return y;
	}

	@Override
	public double getZ () {
		return z;
	}

	@Override
	public void setXYZ (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void setXYZ (Float3 other) {
		this.setXYZ(other.getX(), other.getY(), other.getZ());
	}

	@Override
	public void setZ (double z) {
		this.z = z;
	}

	@Override
	public void setX (double x) {
		this.x = x;
	}

	@Override
	public void setY (double y) {
		this.y = y;
	}

	@Override
	public String toString () {
		return "(" + x + ", " + y + ", " + z + ")";
	}

}
