package com.jfixby.cmns.api.geometry;

import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.math.Angle;
import com.jfixby.cmns.api.math.CustomAngle;

public interface CanvasPosition extends Float2 {

	public CustomAngle getRotation();

	public void setRotation(Angle rotation);

	public void setRotation(double rotation);

	public void setPosition(CanvasPosition other);

}
