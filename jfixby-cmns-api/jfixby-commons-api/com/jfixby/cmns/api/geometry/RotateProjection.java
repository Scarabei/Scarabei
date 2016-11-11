
package com.jfixby.cmns.api.geometry;

import com.jfixby.cmns.api.math.Angle;

public interface RotateProjection extends Projection {

	public void setRotation (Angle rotation);

	public void setRotation (double rotation);

	public Angle getRotation ();

}
