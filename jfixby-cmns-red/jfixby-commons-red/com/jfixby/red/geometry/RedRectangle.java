package com.jfixby.red.geometry;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.CanvasPosition;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.ORIGIN_RELATIVE_HORIZONTAL;
import com.jfixby.cmns.api.geometry.ORIGIN_RELATIVE_VERTICAL;
import com.jfixby.cmns.api.geometry.Rectangle;
import com.jfixby.cmns.api.geometry.RectangleCorner;
import com.jfixby.cmns.api.geometry.Triangle;
import com.jfixby.cmns.api.geometry.Vertex;
import com.jfixby.cmns.api.math.Angle;

public class RedRectangle extends VertexMaster implements Rectangle {
	double width = 0;
	double height = 0;

	final RedVertex top_left = new RedVertex(this);
	final RedVertex top_right = new RedVertex(this);
	final RedVertex bottom_left = new RedVertex(this);
	final RedVertex bottom_right = new RedVertex(this);
	final List<FixedFloat2> vertices = JUtils.newList(top_left, top_right,
			bottom_right, bottom_left);

	final RedPoint origin_relative = new RedPoint();

	@Override
	public String toString() {
		return "Rectangle[" + width + " x " + height + "] origin_relative="
				+ origin_relative;
	}

	private Triangle triangle_a;

	public RedRectangle() {
		super();
		triangle_a = Geometry.newTriangle();
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
		update();
	}

	private void update() {
		top_left.relative().setXY(0 - origin_relative.getX(),
				0 - origin_relative.getY());
		top_right.relative().setXY(1 - origin_relative.getX(),
				0 - origin_relative.getY());
		bottom_right.relative().setXY(1 - origin_relative.getX(),
				1 - origin_relative.getY());
		bottom_left.relative().setXY(0 - origin_relative.getX(),
				1 - origin_relative.getY());

		spread(this.top_left);
		spread(this.top_right);
		spread(this.bottom_left);
		spread(this.bottom_right);

	}

	private void spread(RedVertex spread) {
		spread.relative().setX(spread.relative().getX() * this.width);
		spread.relative().setY(spread.relative().getY() * this.height);
	}

	private void spread(Float2 spread) {
		spread.setX(spread.getX() * this.width);
		spread.setY(spread.getY() * this.height);
	}

	private void unspread(Float2 spread) {
		spread.setX(spread.getX() / this.width);
		spread.setY(spread.getY() / this.height);
	}

	@Override
	public void setWidth(double width) {
		this.setSize(width, height);
	}

	@Override
	public void setHeight(double height) {
		this.setSize(width, height);
	}

	@Override
	public Angle getRotation() {
		return this.getTransform().getRotation();
	}

	@Override
	public void setRotation(Angle rotation) {
		this.getTransform().setRotation(rotation);
	}

	@Override
	public void setRotation(double rotation) {
		this.getTransform().setRotation(rotation);
	}

	@Override
	public void setPosition(double x, double y) {
		this.getTransform().setOffset(x, y);
	}

	@Override
	public void setPositionX(double x) {
		this.getTransform().setOffsetX(x);
	}

	@Override
	public void setPositionY(double y) {
		this.getTransform().setOffsetY(y);
	}

	@Override
	public double getPositionX() {
		return this.getTransform().getOffsetX();
	}

	@Override
	public double getPositionY() {
		return this.getTransform().getOffsetY();
	}

	@Override
	public Rectangle setup(Rectangle other) {
		this.origin_relative.setXY(other.getOriginRelativeX(),
				other.getOriginRelativeY());
		this.width = other.getWidth();
		this.height = other.getHeight();
		this.position.set(other.getPosition());
		this.update();
		// this.getTransform().setup(other.getTransform());
		return this;
	}

	@Override
	public RectangleCorner getTopLeftCorner() {
		return this.top_left;
	}

	@Override
	public RectangleCorner getTopRightCorner() {
		return this.top_right;
	}

	@Override
	public RectangleCorner getBottomLeftCorner() {
		return this.bottom_left;
	}

	@Override
	public RectangleCorner getBottomRightCorner() {
		return this.bottom_right;
	}

	@Override
	public void setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL orX,
			ORIGIN_RELATIVE_VERTICAL orY) {
		this.setOriginRelative(orX.relative_value, orY.relative_value);
	}

	@Override
	public void setOriginRelativeX(ORIGIN_RELATIVE_HORIZONTAL orX) {
		this.setOriginRelative(orX.relative_value, this.origin_relative.getY());
	}

	@Override
	public void setOriginRelativeY(ORIGIN_RELATIVE_VERTICAL orY) {
		this.setOriginRelative(this.origin_relative.getX(), orY.relative_value);
	}

	@Override
	public void setOriginRelativeX(double ORIGIN_POSITION_HORIZONTAL) {
		this.setOriginRelative(ORIGIN_POSITION_HORIZONTAL,
				this.origin_relative.getY());
	}

	@Override
	public void setOriginRelativeY(double ORIGIN_POSITION_VERTICAL) {
		this.setOriginRelative(this.origin_relative.getX(),
				ORIGIN_POSITION_VERTICAL);

	}

	@Override
	public void setOriginAbsolute(double origin_x, double origin_y) {
		final boolean new_method = !true;

		if (new_method) {
			this.tmp.setXY(origin_x, origin_y);
			this.toRelative(tmp);
			this.origin_relative.setXY(
					this.origin_relative.getX() + this.tmp.getX(),
					this.origin_relative.getY() + this.tmp.getY());
		} else {
			this.tmp.setXY(origin_x, origin_y);
			// Geometry.applyTransformation(this.absolute_to_relative_matrix,
			// this.tmp);
			this.getTransform().reverse(this.tmp);
			unspread(this.tmp);

			this.origin_relative.setXY(
					this.origin_relative.getX() + this.tmp.getX(),
					this.origin_relative.getY() + this.tmp.getY());
		}
		// this.position.set(origin_x, origin_y);
		this.setPosition(origin_x, origin_y);
		this.update();

	}

	final Float2 tmp = Geometry.newFloat2();
	private CanvasPosition position = Geometry.newCanvasPosition();;

	@Override
	public void setOriginRelative(double ORIGIN_POSITION_HORIZONTAL,
			double ORIGIN_POSITION_VERTICAL) {
		final boolean new_method = !true;
		if (new_method) {
			this.tmp.setXY(ORIGIN_POSITION_HORIZONTAL, ORIGIN_POSITION_VERTICAL);
			this.toAbsolute(tmp);
			this.setPositionXY(tmp);
		} else {
			this.tmp.setXY(
					ORIGIN_POSITION_HORIZONTAL - this.origin_relative.getX(),
					ORIGIN_POSITION_VERTICAL - this.origin_relative.getY());// relative
																			// delta
																			// Geometry.applyTransformation(this.retalive_to_absolute_matrix,
																			// tmp);//
																			// new
			spread(tmp);
			this.getTransform().transform(tmp);

			// world
			// position
			this.setPositionXY(tmp);
		}
		this.origin_relative.setXY(ORIGIN_POSITION_HORIZONTAL,
				ORIGIN_POSITION_VERTICAL);
		this.update();
	}

	@Override
	public void toAbsolute(Float2 temp_point) {
		temp_point.setXY(temp_point.getX() - this.origin_relative.getX(),
				temp_point.getY() - this.origin_relative.getY());// relative
		spread(temp_point);
		this.getTransform().transform(temp_point);

		// Geometry.applyTransformation(this.retalive_to_absolute_matrix,
		// temp_point);// new
	}

	@Override
	public void toRelative(Float2 temp_point) {
		// Geometry.applyTransformation(this.absolute_to_relative_matrix,
		// temp_point);

		this.getTransform().reverse(temp_point);
		unspread(temp_point);

		temp_point.setXY(temp_point.getX() + this.origin_relative.getX(),
				temp_point.getY() + this.origin_relative.getY());// relative
	}

	@Override
	public void setOriginAbsoluteX(double origin_x) {
		this.setOriginAbsolute(origin_x, this.getPositionY());
	}

	@Override
	public void setOriginAbsoluteY(double origin_y) {
		this.setOriginAbsolute(this.getPositionX(), origin_y);
	}

	@Override
	public CanvasPosition getPosition() {
		position.setXY(this.getPositionX(), this.getPositionY());
		position.setRotation(this.getRotation());
		return position;
	}

	@Override
	public void setCanvasPosition(CanvasPosition position) {
		this.setPosition(position.getX(), position.getY());
		this.setRotation(position.getRotation());
	}

	@Override
	public boolean containsPoint(double canvas_x, double canvas_y) {
		this.triangle_a.A().relative().set(this.top_left.transformed());
		this.triangle_a.B().relative().set(this.top_right.transformed());
		this.triangle_a.C().relative().set(this.bottom_right.transformed());

		if (this.triangle_a.containsPoint(canvas_x, canvas_y)) {
			return true;
		}

		this.triangle_a.B().relative().set(this.bottom_left.transformed());

		if (this.triangle_a.containsPoint(canvas_x, canvas_y)) {
			return true;
		}

		return false;

	}

	@Override
	public boolean containsPoint(FixedFloat2 point) {
		return this.containsPoint(point.getX(), point.getY());
	}

	@Override
	public double getOriginRelativeX() {
		return this.origin_relative.getX();
	}

	@Override
	public double getOriginRelativeY() {
		return this.origin_relative.getY();
	}

	@Override
	public void setPositionXY(FixedFloat2 position) {
		this.setPosition(position.getX(), position.getY());
	}

	@Override
	public void setOriginAbsolute(FixedFloat2 tmp) {
		this.setOriginAbsolute(tmp.getX(), tmp.getY());
	}

	@Override
	public Collection<FixedFloat2> listVertices() {
		return vertices;
	}

}
