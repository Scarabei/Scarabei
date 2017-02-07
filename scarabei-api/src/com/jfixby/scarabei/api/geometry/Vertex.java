
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.floatn.Float2;

public interface Vertex extends GeometryFigure, ReadOnlyFloat2 {

	Edge getRightEdge ();

	Edge getLeftEdge ();

	public ReadOnlyFloat2 transformed ();

	public Float2 relative ();

}
