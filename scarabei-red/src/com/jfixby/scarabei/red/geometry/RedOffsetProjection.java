
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.Rectangle;
import com.jfixby.scarabei.api.geometry.projections.OffsetProjection;

public final class RedOffsetProjection implements OffsetProjection {

// private double offsetX;
// private double offsetY;
// final Float2 offset = Geometry.newFloat2();
	private final Rectangle rectangle;

	public RedOffsetProjection (final Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public RedOffsetProjection () {
		this.rectangle = Geometry.newRectangle();
	}

	public ReadOnlyFloat2 getOffset () {
// this.rectangle.setPositionXY(x);
		return this.rectangle.getPosition();
	}

	@Override
	public void setOffsetX (final double x) {
		this.rectangle.setPositionX(x);
	}

	@Override
	public void setOffsetY (final double y) {
		this.rectangle.setPositionY(y);
	}

	@Override
	public double getOffsetX () {
		return this.rectangle.getPositionX();
	}

	@Override
	public double getOffsetY () {
		return this.rectangle.getPositionY();
	}

	@Override
	public void project (final Float2 point) {
		point.setX(point.getX() + this.rectangle.getPositionX());
		point.setY(point.getY() + this.rectangle.getPositionY());
	}

	public void setOffset (final CanvasPosition position) {
		this.rectangle.setPositionX(position.getX());
		this.rectangle.setPositionY(position.getY());
	}

	@Override
	public void setOffsetXY (final double x, final double y) {
		this.setOffsetX(x);
		this.setOffsetY(y);
	}

	@Override
	public void unProject (final Float2 point) {
		point.setX(point.getX() - this.rectangle.getPositionX());
		point.setY(point.getY() - this.rectangle.getPositionY());
	}

}
