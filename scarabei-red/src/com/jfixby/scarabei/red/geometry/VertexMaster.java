
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.geometry.GeometricShape;

public class VertexMaster implements GeometricShape {

	final private RedTransform transform = new RedTransform();

	public RedTransform getTransform () {
		return this.transform;
	}

}
