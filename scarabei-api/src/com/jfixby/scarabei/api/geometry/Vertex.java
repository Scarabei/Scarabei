
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.floatn.FixedFloat2;
import com.jfixby.scarabei.api.floatn.Float2;

public interface Vertex extends GeometryFigure, FixedFloat2 {

	Edge getRightEdge ();

	Edge getLeftEdge ();

	public FixedFloat2 transformed ();

	public Float2 relative ();

}
