
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.OffsetProjection;

public final class RedOffsetProjection implements OffsetProjection {

	private double offsetX;
	private double offsetY;

	@Override
	public void setOffsetX (final double x) {
		this.offsetX = x;
	}

	@Override
	public void setOffsetY (final double y) {
		this.offsetY = y;
	}

	@Override
	public double getOffsetX () {
		return this.offsetX;
	}

	@Override
	public double getOffsetY () {
		return this.offsetY;
	}

	@Override
	public void project (final Float2 point) {
		point.setX(point.getX() + this.offsetX);
		point.setY(point.getY() + this.offsetY);
	}

}
