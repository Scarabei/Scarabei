
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.math.Angle;

public interface Projectable extends GeometryFigure {
	double getWidth ();

	double getHeight ();

	void setOrigin (ORIGIN_RELATIVE_HORIZONTAL orX, ORIGIN_RELATIVE_VERTICAL orY);

	void setOriginX (ORIGIN_RELATIVE_HORIZONTAL orX);

	void setOriginY (ORIGIN_RELATIVE_VERTICAL orY);

	void setOrigin (double ORIGIN_POSITION_HORIZONTAL, double ORIGIN_POSITION_VERTICAL);

	void setOriginX (double ORIGIN_POSITION_HORIZONTAL);

	void setOriginY (double ORIGIN_POSITION_VERTICAL);

	public Angle getRotation ();

	public double getPositionX ();

	public double getPositionY ();

	RectangleCorner getTopLeftCorner ();

	RectangleCorner getTopRightCorner ();

	RectangleCorner getBottomLeftCorner ();

	RectangleCorner getBottomRightCorner ();

	public void setRotation (Angle rotation);

	public void setRotation (double rotation);

	public void setPositionXY (double x, double y);

	public void setPositionX (double x);

	public void setPositionY (double y);
}
