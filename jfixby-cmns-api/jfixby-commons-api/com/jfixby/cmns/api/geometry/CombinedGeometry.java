package com.jfixby.cmns.api.geometry;


public interface CombinedGeometry extends GeometryFigure {

	void addFigure(GeometryFigure child);

	int size();

}
