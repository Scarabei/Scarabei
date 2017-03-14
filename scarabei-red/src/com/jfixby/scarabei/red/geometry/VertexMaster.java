
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.geometry.GeometryFigure;

public class VertexMaster implements GeometryFigure {

	final private RedTransform transform = new RedTransform();

	public RedTransform getTransform () {
		return this.transform;
	}

}
