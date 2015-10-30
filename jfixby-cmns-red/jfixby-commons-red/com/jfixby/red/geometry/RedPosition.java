package com.jfixby.red.geometry;

import com.jfixby.cmns.api.angles.Angles;
import com.jfixby.cmns.api.geometry.CanvasPosition;
import com.jfixby.cmns.api.math.Angle;
import com.jfixby.cmns.api.math.CustomAngle;

public class RedPosition extends RedPoint implements CanvasPosition {
	final CustomAngle rotation = Angles.newAngle();

	@Override
	public CustomAngle getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(Angle rotation) {
		this.rotation.setValue(rotation);
	}

	@Override
	public void setRotation(double rotation) {
		this.rotation.setValue(rotation);
	}

	@Override
	public void setPosition(CanvasPosition other) {
		this.set(other);
		this.setRotation(other.getRotation());
	}

}
