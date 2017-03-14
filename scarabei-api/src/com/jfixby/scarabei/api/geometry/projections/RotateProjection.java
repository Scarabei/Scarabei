
package com.jfixby.scarabei.api.geometry.projections;

import com.jfixby.scarabei.api.math.Angle;

public interface RotateProjection extends Projection {

	public void setRotation (Angle rotation);

	public void setRotation (double rotation);

	public Angle getRotation ();

}
