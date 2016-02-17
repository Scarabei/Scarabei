package com.jfixby.red.geometry;

import com.jfixby.cmns.api.geometry.GeometryFigure;
import com.jfixby.cmns.api.transform.CanvasTransform;

public class VertexMaster implements GeometryFigure {

	final private RedTransform transform = new RedTransform();

	public CanvasTransform getTransform() {
		return transform;
	}

}
