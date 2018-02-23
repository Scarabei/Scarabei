
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;

public interface Vertex extends GeometricShape, ReadOnlyFloat2 {

	Edge getRightEdge ();

	Edge getLeftEdge ();

	public ReadOnlyFloat2 transformed ();

	public Float2 relative ();

}
