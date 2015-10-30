package com.jfixby.cmns.api.geometry;

import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;

public interface Vertex extends GeometryFigure, FixedFloat2 {

	Edge getRightEdge();

	Edge getLeftEdge();

	public FixedFloat2 transformed();

	public Float2 relative();

}
