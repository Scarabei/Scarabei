
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.projections.ScaleProjection;

public class RedScaleProjection implements ScaleProjection {
	double scaleX = 1;
	double scaleY = 1;

	@Override
	public void project (final Float2 point) {
		point.setX(point.getX() * this.scaleX);
		point.setY(point.getY() * this.scaleY);
	}

	@Override
	public void setScaleX (final double scaleX) {
		this.scaleX = scaleX;
	}

	@Override
	public void setScaleY (final double scaleY) {
		this.scaleY = scaleY;
	}

	@Override
	public void setScaleXY (final double w, final double h) {
		this.setScaleX(w);
		this.setScaleY(h);
	}

	@Override
	public void unProject (final Float2 point) {
		if (this.scaleX != 0) {
			point.setX(point.getX() / this.scaleX);
		} else {
			point.setX(Double.MAX_VALUE);
		}
		if (this.scaleY != 0) {
			point.setY(point.getY() / this.scaleY);
		} else {
			point.setY(Double.MAX_VALUE);
		}
	}

}
