
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.math.Angle;

public interface Circle extends GeometryFigure {

	public double getRadius ();

	public void setRadius (double radius);

	public Vertex getVertexAt (Angle direction);

	public Vertex getVertexAt (double direction_radians);

	public void setXY (double x, double y);

	public void setRotation (double rotation);

	public double getPositionX ();

	public double getPositionY ();

	public void setPosition (double x, double y);

	public Vertex getCenter ();

}
