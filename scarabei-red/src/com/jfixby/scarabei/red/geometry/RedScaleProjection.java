
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

}
