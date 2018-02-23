
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.math.Angle;

public interface Rectangle extends GeometricShape {
	double getWidth ();

	double getHeight ();

	public Angle getRotation ();

	public double getPositionX ();

	public double getPositionY ();

	public double getOriginRelativeX ();

	public double getOriginRelativeY ();

	RectangleCorner getTopLeftCorner ();

	RectangleCorner getTopRightCorner ();

	RectangleCorner getBottomLeftCorner ();

	RectangleCorner getBottomRightCorner ();

	CanvasPosition getPosition ();

	boolean containsPoint (double canvas_x, double canvas_y);

	Collection<ReadOnlyFloat2> listVertices ();

	boolean containsPoint (ReadOnlyFloat2 canvas_point);

	void toRelative (Float2 temp_point);

	void toAbsolute (Float2 temp_point);

	//
	void setSize (double width, double height);

	void setWidth (double width);

	void setHeight (double height);

	void setOriginAbsolute (double origin_x, double origin_y);

	void setOriginAbsoluteX (double origin_x);

	void setOriginAbsoluteY (double origin_y);

	void setOriginRelative (ORIGIN_RELATIVE_HORIZONTAL ORIGIN_RELATIVE_HORIZONTAL,
		ORIGIN_RELATIVE_VERTICAL ORIGIN_RELATIVE_VERTICAL);

	void setOriginRelative ();

	void setOriginRelativeX (ORIGIN_RELATIVE_HORIZONTAL ORIGIN_RELATIVE_HORIZONTAL);

	void setOriginRelativeY (ORIGIN_RELATIVE_VERTICAL ORIGIN_RELATIVE_VERTICAL);

	void setOriginRelative (double ORIGIN_RELATIVE_HORIZONTAL, double ORIGIN_POSITION_VERTICAL);

	void setOriginRelativeX (double ORIGIN_RELATIVE_HORIZONTAL);

	void setOriginRelativeY (double ORIGIN_POSITION_VERTICAL);

	public void setRotation (Angle rotation);

	public void setRotation (double rotation);

	public void setPosition (double x, double y);

	public void setPositionX (double x);

	public void setPositionY (double y);

	public Rectangle setup (Rectangle other);

	void setPosition (CanvasPosition position);

	void setPosition (ReadOnlyFloat2 position);

	void setOriginAbsolute (ReadOnlyFloat2 tmp);

	void reScale (double scaleX, double scaleY);

	void setPosition ();

	void setSize (Rectangle rectangle);

}
