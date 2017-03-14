
package com.jfixby.scarabei.api.geometry.projections;

import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.CanvasPosition;

public interface RotateAndOffsetProjection extends OffsetProjection, RotateProjection {

	ReadOnlyFloat2 getOffset ();

	void set (CanvasPosition position);

}
