
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.CustomAngle;

public interface CanvasPosition extends Float2, ReadOnlyCanvasPosition {

	public CustomAngle getRotation ();

	public void setRotation (Angle rotation);

	public void setRotation (double rotation);

	public void setPosition (CanvasPosition other);

	public void setPosition (ReadOnlyFloat2 other);

	public void setPosition ();

}
