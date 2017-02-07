
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.angles.Angles;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.CustomAngle;

public class RedPosition extends RedPoint implements CanvasPosition {
	final CustomAngle rotation = Angles.newAngle();

	@Override
	public CustomAngle getRotation () {
		return this.rotation;
	}

	@Override
	public void setRotation (final Angle rotation) {
		this.rotation.setValue(rotation);
	}

	@Override
	public void setRotation (final double rotation) {
		this.rotation.setValue(rotation);
	}

	@Override
	public void setPosition (final CanvasPosition other) {
		this.set(other);
		this.setRotation(other.getRotation());
	}

	@Override
	public void setPosition () {
		this.setXY(0, 0);
		this.setRotation(0);
	}

	@Override
	public void setPosition (final ReadOnlyFloat2 other) {
		this.set(other);
	}

}
