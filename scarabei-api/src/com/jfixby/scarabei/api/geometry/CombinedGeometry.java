
package com.jfixby.scarabei.api.geometry;

public interface CombinedGeometry extends GeometricShape {

	void addFigure (GeometricShape child);

	int size ();

}
