package com.jfixby.cmns.api.geometry;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.math.Angle;

public interface Rectangle extends GeometryFigure {

	double getWidth();

	double getHeight();

	void setSize(double width, double height);

	void setWidth(double width);

	void setHeight(double height);

	void setOriginAbsolute(double origin_x, double origin_y);

	void setOriginAbsoluteX(double origin_x);

	void setOriginAbsoluteY(double origin_y);

	void setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL orX,
			ORIGIN_RELATIVE_VERTICAL orY);

	void setOriginRelativeX(ORIGIN_RELATIVE_HORIZONTAL orX);

	void setOriginRelativeY(ORIGIN_RELATIVE_VERTICAL orY);

	void setOriginRelative(double ORIGIN_POSITION_HORIZONTAL,
			double ORIGIN_POSITION_VERTICAL);

	void setOriginRelativeX(double ORIGIN_POSITION_HORIZONTAL);

	void setOriginRelativeY(double ORIGIN_POSITION_VERTICAL);

	public Angle getRotation();

	public void setRotation(Angle rotation);

	public void setRotation(double rotation);

	public void setPosition(double x, double y);

	public void setPositionX(double x);

	public void setPositionY(double y);

	public double getPositionX();

	public double getPositionY();

	public Rectangle setup(Rectangle other);

	RectangleCorner getTopLeftCorner();

	RectangleCorner getTopRightCorner();

	RectangleCorner getBottomLeftCorner();

	RectangleCorner getBottomRightCorner();

	CanvasPosition getPosition();

	void setCanvasPosition(CanvasPosition position);

	void setPositionXY(FixedFloat2 position);

	boolean containsPoint(double canvas_x, double canvas_y);

	boolean containsPoint(FixedFloat2 canvas_point);

	public double getOriginRelativeX();

	public double getOriginRelativeY();

	void toRelative(Float2 temp_point);

	void toAbsolute(Float2 temp_point);

	void setOriginAbsolute(FixedFloat2 tmp);

	Collection<FixedFloat2> listVertices();

}
