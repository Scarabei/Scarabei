
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.Rectangle;
import com.jfixby.scarabei.api.geometry.projections.RotateAndOffsetProjection;
import com.jfixby.scarabei.api.math.Angle;

public class RedRectangleProjection extends RedProjectionsStack implements RotateAndOffsetProjection {

	final RedOffsetProjection offset;
	final RedRotateProjection rotate;

	public RedRectangleProjection (final Rectangle rectangle) {
		this.offset = new RedOffsetProjection(rectangle);
		this.rotate = new RedRotateProjection(rectangle);

		this.push(this.offset);
		this.push(this.rotate);
	}

	@Override
	public void setOffsetX (final double x) {
		this.offset.setOffsetX(x);
	}

	@Override
	public void setOffsetY (final double y) {
		this.offset.setOffsetY(y);
	}

	@Override
	public double getOffsetX () {
		return this.offset.getOffsetX();
	}

	@Override
	public double getOffsetY () {
		return this.offset.getOffsetY();
	}

	@Override
	public void setRotation (final Angle rotation) {
		this.rotate.setRotation(rotation);
	}

	@Override
	public void setRotation (final double rotation) {
		this.rotate.setRotation(rotation);
	}

	@Override
	public Angle getRotation () {
		return this.rotate.getRotation();
	}

	@Override
	public ReadOnlyFloat2 getOffset () {
		return this.offset.getOffset();
	}

	@Override
	public void set (final CanvasPosition position) {
		this.offset.setOffset(position);
		this.rotate.setRotation(position.getRotation());
	}

	@Override
	public void setOffsetXY (final double x, final double y) {
		this.offset.setOffsetXY(x, y);
	}

}
