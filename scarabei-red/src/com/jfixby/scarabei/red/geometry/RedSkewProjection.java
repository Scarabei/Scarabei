
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.angles.Angles;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.projections.SkewProjection;
import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.CustomAngle;

public class RedSkewProjection implements SkewProjection {

	final CustomAngle skewX = Angles.newAngle();
	final CustomAngle skewY = Angles.newAngle();
	double x = 0;
	double y = 0;

	@Override
	public void project (final Float2 point) {
		this.x = point.getX();
		this.y = point.getY();
		point.setX(this.x + Math.sin(this.skewX.toRadians()) * this.y);
		point.setY(this.y + Math.sin(this.skewY.toRadians()) * this.x);

	}

	@Override
	public void setSkewX (final Angle skewx) {
		this.skewX.setValue(skewx);
	}

	@Override
	public void setSkewY (final Angle skewy) {
		this.skewY.setValue(skewy);
	}

	@Override
	public void unProject (final Float2 point) {
		this.x = point.getX();
		this.y = point.getY();
		point.setX(this.x + Math.sin(-this.skewX.toRadians()) * this.y);
		point.setY(this.y + Math.sin(-this.skewY.toRadians()) * this.x);

	}

}
