
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.projections.OffsetProjection;

public final class RedOffsetProjection implements OffsetProjection {

	private double offsetX;
	private double offsetY;
	final Float2 offset = Geometry.newFloat2();

	public ReadOnlyFloat2 getOffset () {
		this.offset.setXY(this.offsetX, this.offsetY);
		return this.offset;
	}

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

	public void setOffset (final CanvasPosition position) {
		this.offsetX = position.getX();
		this.offsetY = position.getY();
	}

	@Override
	public void setOffsetXY (final double x, final double y) {
		this.setOffsetX(x);
		this.setOffsetY(y);
	}

	@Override
	public void unProject (final Float2 point) {
		point.setX(point.getX() - this.offsetX);
		point.setY(point.getY() - this.offsetY);
	}

}
