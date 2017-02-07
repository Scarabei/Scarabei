
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.math.Angle;

public interface ReadOnlyCanvasPosition extends ReadOnlyFloat2 {

	public Angle getRotation ();

}
