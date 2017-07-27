
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.ORIGIN_RELATIVE_HORIZONTAL;
import com.jfixby.scarabei.api.geometry.ORIGIN_RELATIVE_VERTICAL;
import com.jfixby.scarabei.api.geometry.Rectangle;
import com.jfixby.scarabei.api.geometry.RectangleCorner;
import com.jfixby.scarabei.api.geometry.Triangle;
import com.jfixby.scarabei.api.math.Angle;

public class RedRectangle extends VertexMaster implements Rectangle {
	double width = 0;
	double height = 0;

	final RedVertex top_left = new RedVertex(this);
	final RedVertex top_right = new RedVertex(this);
	final RedVertex bottom_left = new RedVertex(this);
	final RedVertex bottom_right = new RedVertex(this);
	final RedTransform transform = new RedTransform();
	final List<ReadOnlyFloat2> vertices = Collections.newList((ReadOnlyFloat2)this.top_left, (ReadOnlyFloat2)this.top_right,
		(ReadOnlyFloat2)this.bottom_right, (ReadOnlyFloat2)this.bottom_left);

	final RedPoint origin_relative = new RedPoint();

	@Override
	public String toString () {
		return "Rectangle[" + this.width + " x " + this.height + "] position=" + //
			this.getPosition()//
			+ " origin_relative="//
			+ this.origin_relative + " rotation=" + this.getRotation();
	}

	private final Triangle triangle_a;

	public RedRectangle () {
		super();
		this.triangle_a = Geometry.newTriangle();
	}

	@Override
	public double getWidth () {
		return this.width;
	}

	@Override
	public double getHeight () {
		return this.height;
	}

	@Override
	public void setSize (final double width, final double height) {
		this.width = width;
		this.height = height;
		this.update();
	}

	private void update () {
		this.top_left.relative().setXY(0 - this.origin_relative.getX(), 0 - this.origin_relative.getY());
		this.top_right.relative().setXY(1 - this.origin_relative.getX(), 0 - this.origin_relative.getY());
		this.bottom_right.relative().setXY(1 - this.origin_relative.getX(), 1 - this.origin_relative.getY());
		this.bottom_left.relative().setXY(0 - this.origin_relative.getX(), 1 - this.origin_relative.getY());

		this.spread(this.top_left);
		this.spread(this.top_right);
		this.spread(this.bottom_left);
		this.spread(this.bottom_right);

	}

	private void spread (final RedVertex spread) {
		spread.relative().setX(spread.relative().getX() * this.width);
		spread.relative().setY(spread.relative().getY() * this.height);
	}

	private void spread (final Float2 spread) {
		spread.setX(spread.getX() * this.width);
		spread.setY(spread.getY() * this.height);
	}

	private void unspread (final Float2 spread) {
		spread.setX(spread.getX() / this.width);
		spread.setY(spread.getY() / this.height);
	}

	@Override
	public void setWidth (final double width) {
		this.setSize(width, this.height);
	}

	@Override
	public void setHeight (final double height) {
		this.setSize(this.width, height);
	}

	@Override
	public Angle getRotation () {
		return this.getTransform().getRotation();
	}

	@Override
	public void setRotation (final Angle rotation) {
		this.getTransform().setRotation(rotation);
	}

	@Override
	public void setRotation (final double rotation) {
		this.getTransform().setRotation(rotation);
	}

	@Override
	public void setPosition (final double x, final double y) {
		this.getTransform().setOffset(x, y);
	}

	@Override
	public void setPositionX (final double x) {
		this.getTransform().setOffsetX(x);
	}

	@Override
	public void setPositionY (final double y) {
		this.getTransform().setOffsetY(y);
	}

	@Override
	public RedTransform getTransform () {
		return this.transform;
	}

	@Override
	public double getPositionX () {
		return this.getTransform().getOffsetX();
	}

	@Override
	public double getPositionY () {
		return this.getTransform().getOffsetY();
	}

	@Override
	public Rectangle setup (final Rectangle other) {
		this.setOriginRelativeX(other.getOriginRelativeX());
		this.setOriginRelativeY(other.getOriginRelativeY());
		this.setPosition(other.getPosition());
		this.setSize(other);

		// this.position.setXY(0, 0);
// this.origin_relative.setXY(0, 0);
// this.origin_relative.setXY(other.getOriginRelativeX(), other.getOriginRelativeY());
// this.width = other.getWidth();
// this.height = other.getHeight();
// this.position.set(other.getPosition());
// this.update();
// // this.getTransform().setup(other.getTransform());
		return this;
	}

	@Override
	public void setOriginRelative () {
		this.setOriginRelative(0, 0);
	}

	@Override
	public RectangleCorner getTopLeftCorner () {
		return this.top_left;
	}

	@Override
	public RectangleCorner getTopRightCorner () {
		return this.top_right;
	}

	@Override
	public RectangleCorner getBottomLeftCorner () {
		return this.bottom_left;
	}

	@Override
	public RectangleCorner getBottomRightCorner () {
		return this.bottom_right;
	}

	@Override
	public void setOriginRelative (final ORIGIN_RELATIVE_HORIZONTAL orX, final ORIGIN_RELATIVE_VERTICAL orY) {
		this.setOriginRelative(orX.relative_value, orY.relative_value);
	}

	@Override
	public void setOriginRelativeX (final ORIGIN_RELATIVE_HORIZONTAL orX) {
		this.setOriginRelative(orX.relative_value, this.origin_relative.getY());
	}

	@Override
	public void setOriginRelativeY (final ORIGIN_RELATIVE_VERTICAL orY) {
		this.setOriginRelative(this.origin_relative.getX(), orY.relative_value);
	}

	@Override
	public void setOriginRelativeX (final double ORIGIN_POSITION_HORIZONTAL) {
		this.setOriginRelative(ORIGIN_POSITION_HORIZONTAL, this.origin_relative.getY());
	}

	@Override
	public void setOriginRelativeY (final double ORIGIN_POSITION_VERTICAL) {
		this.setOriginRelative(this.origin_relative.getX(), ORIGIN_POSITION_VERTICAL);

	}

	@Override
	public void setOriginAbsolute (final double origin_x, final double origin_y) {
		final boolean new_method = !true;

		if (new_method) {
			this.tmp.setXY(origin_x, origin_y);
			this.toRelative(this.tmp);
			this.origin_relative.setXY(this.origin_relative.getX() + this.tmp.getX(), this.origin_relative.getY() + this.tmp.getY());
		} else {
			this.tmp.setXY(origin_x, origin_y);
			// Geometry.applyTransformation(this.absolute_to_relative_matrix,
			// this.tmp);
			this.getTransform().reverse(this.tmp);
			this.unspread(this.tmp);

			this.origin_relative.setXY(this.origin_relative.getX() + this.tmp.getX(), this.origin_relative.getY() + this.tmp.getY());
		}
		// this.position.set(origin_x, origin_y);
		this.setPosition(origin_x, origin_y);
		this.update();

	}

	final Float2 tmp = Geometry.newFloat2();
	private final CanvasPosition position = Geometry.newCanvasPosition();;

	@Override
	public void setOriginRelative (final double ORIGIN_POSITION_HORIZONTAL, final double ORIGIN_POSITION_VERTICAL) {
		final boolean new_method = !true;
		if (new_method) {
			this.tmp.setXY(ORIGIN_POSITION_HORIZONTAL, ORIGIN_POSITION_VERTICAL);
			this.toAbsolute(this.tmp);
			this.setPosition(this.tmp);
		} else {
			this.tmp.setXY(ORIGIN_POSITION_HORIZONTAL - this.origin_relative.getX(),
				ORIGIN_POSITION_VERTICAL - this.origin_relative.getY());// relative
			// delta
			// Geometry.applyTransformation(this.retalive_to_absolute_matrix,
			// tmp);//
			// new
			this.spread(this.tmp);
			this.getTransform().transform(this.tmp);

			// world
			// position
			this.setPosition(this.tmp);
		}
		this.origin_relative.setXY(ORIGIN_POSITION_HORIZONTAL, ORIGIN_POSITION_VERTICAL);
		this.update();
	}

	@Override
	public void toAbsolute (final Float2 temp_point) {
		temp_point.setXY(temp_point.getX() - this.origin_relative.getX(), temp_point.getY() - this.origin_relative.getY());// relative
		this.spread(temp_point);
		this.getTransform().transform(temp_point);

		// Geometry.applyTransformation(this.retalive_to_absolute_matrix,
		// temp_point);// new
	}

	@Override
	public void toRelative (final Float2 temp_point) {
		// Geometry.applyTransformation(this.absolute_to_relative_matrix,
		// temp_point);

		this.getTransform().reverse(temp_point);
		this.unspread(temp_point);

		temp_point.setXY(temp_point.getX() + this.origin_relative.getX(), temp_point.getY() + this.origin_relative.getY());// relative
	}

	@Override
	public void setOriginAbsoluteX (final double origin_x) {
		this.setOriginAbsolute(origin_x, this.getPositionY());
	}

	@Override
	public void setOriginAbsoluteY (final double origin_y) {
		this.setOriginAbsolute(this.getPositionX(), origin_y);
	}

	@Override
	public CanvasPosition getPosition () {
		this.position.setXY(this.getPositionX(), this.getPositionY());
		this.position.setRotation(this.getRotation());
		return this.position;
	}

	@Override
	public void setPosition (final CanvasPosition position) {
		this.setPosition(position.getX(), position.getY());
		this.setRotation(position.getRotation());
	}

	@Override
	public boolean containsPoint (final double canvas_x, final double canvas_y) {
		this.tmp.setXY(canvas_x, canvas_y);
		this.toRelative(this.tmp);
		if (this.tmp.getX() > 1) {
			return false;
		}
		if (this.tmp.getX() < 0) {
			return false;
		}
		if (this.tmp.getY() > 1) {
			return false;
		}
		if (this.tmp.getY() < 0) {
			return false;
		}

		return true;

	}

	@Override
	public boolean containsPoint (final ReadOnlyFloat2 point) {
		return this.containsPoint(point.getX(), point.getY());
	}

	@Override
	public double getOriginRelativeX () {
		return this.origin_relative.getX();
	}

	@Override
	public double getOriginRelativeY () {
		return this.origin_relative.getY();
	}

	@Override
	public void setPosition (final ReadOnlyFloat2 position) {
		this.setPosition(position.getX(), position.getY());
	}

	@Override
	public void setOriginAbsolute (final ReadOnlyFloat2 tmp) {
		this.setOriginAbsolute(tmp.getX(), tmp.getY());
	}

	@Override
	public Collection<ReadOnlyFloat2> listVertices () {
		return this.vertices;
	}

	@Override
	public void reScale (final double scaleX, final double scaleY) {
		this.setWidth(this.getWidth() * scaleX);
		this.setHeight(this.getHeight() * scaleY);
	}

	@Override
	public void setPosition () {
		this.position.setPosition();
	}

	@Override
	public void setSize (final Rectangle rectangle) {
		this.setSize(rectangle.getWidth(), rectangle.getHeight());
	}

}
