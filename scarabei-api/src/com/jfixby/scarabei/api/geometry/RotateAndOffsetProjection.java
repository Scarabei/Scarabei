
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;

public interface RotateAndOffsetProjection extends OffsetProjection, RotateProjection {

	ReadOnlyFloat2 getOffset ();

	void set (CanvasPosition position);

}
