
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.geometry.GeometryFigure;
import com.jfixby.scarabei.api.transform.CanvasTransform;

public class VertexMaster implements GeometryFigure {

	final private RedTransform transform = new RedTransform();

	public CanvasTransform getTransform () {
		return transform;
	}

}
