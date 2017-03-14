
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.projections.CanvasTransform;
import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.MathTools;
import com.jfixby.scarabei.api.math.VectorTool;

public class RedTransform implements CanvasTransform {

	// private Matrix scale_matrix;
	// private Matrix rotation_matrix;
	// private Matrix offset_matrix;
	// private Matrix skew_matrix;
	//
	// private Matrix un_scale_matrix;
	// private Matrix un_rotation_matrix;
	// private Matrix un_offset_matrix;
	// private Matrix un_skew_matrix;
	//
	// private boolean need_setup = true;;

	final private Float2 scale = Geometry.newFloat2(1, 1);
	final private Float2 skew = Geometry.newFloat2();
	final private RedPosition position = new RedPosition();
	// final private CustomAngle rotation = Angles.newAngle();
	final private VectorTool vector_tool = MathTools.newVectorTool();

	RedTransform () {

		// scale_matrix = MathTools.newIdentityMatrix(3);
		// skew_matrix = MathTools.newIdentityMatrix(3);
		// rotation_matrix = MathTools.newIdentityMatrix(3);
		// offset_matrix = MathTools.newIdentityMatrix(3);
		//
		// un_scale_matrix = MathTools.newIdentityMatrix(3);
		// un_skew_matrix = MathTools.newIdentityMatrix(3);
		// un_rotation_matrix = MathTools.newIdentityMatrix(3);
		// un_offset_matrix = MathTools.newIdentityMatrix(3);
	}

	@Override
	public void transform (final Float2 temp_point) {
		temp_point.setX(temp_point.getX() * this.scale.getX());
		temp_point.setY(temp_point.getY() * this.scale.getY());

		this.vector_tool.X = temp_point.getX();
		this.vector_tool.Y = temp_point.getY();
		this.vector_tool.XYtoAR();
		this.vector_tool.A = this.vector_tool.A + this.position.getRotation().toRadians();
		this.vector_tool.ARtoXY();
		temp_point.setX(this.vector_tool.X + this.position.getX());
		temp_point.setY(this.vector_tool.Y + this.position.getY());

		// setup();
		//
		// Geometry.applyTransformation(scale_matrix, temp_point);
		// Geometry.applyTransformation(skew_matrix, temp_point);
		// Geometry.applyTransformation(rotation_matrix, temp_point);
		// Geometry.applyTransformation(offset_matrix, temp_point);

	}

	private void setup () {
		// if (!need_setup) {
		// return;
		// }
		// boolean log = true;
		//
		// MathTools.setupScaleMatrix(scale_matrix, this.getScaleX(),
		// this.getScaleY(), 1d);
		// if (log)
		// scale_matrix.print("scale_matrix");
		// MathTools.setupRotationMatrix(rotation_matrix, this.position
		// .getRotation().toRadians());
		// if (log)
		// rotation_matrix.print("rotation_matrix");
		// MathTools.setupOffsetMatrix(offset_matrix, this.position.getX(),
		// this.position.getY());
		// if (log)
		// offset_matrix.print("offset_matrix");
		// MathTools
		// .setupSkewMatrix(skew_matrix, this.getSkewX(), this.getSkewY());
		// if (log)
		// skew_matrix.print("skew_matrix");
		//
		// MathTools.inverse(scale_matrix, un_scale_matrix);
		// MathTools.inverse(rotation_matrix, un_rotation_matrix);
		// MathTools.inverse(offset_matrix, un_offset_matrix);
		// MathTools.inverse(skew_matrix, un_skew_matrix);
		//
		// need_setup = false;
	}

	@Override
	public void reverse (final Float2 temp_point) {
		this.vector_tool.X = temp_point.getX() - this.position.getX();
		this.vector_tool.Y = temp_point.getY() - this.position.getY();
		this.vector_tool.XYtoAR();
		this.vector_tool.A = this.vector_tool.A - this.position.getRotation().toRadians();
		this.vector_tool.ARtoXY();
		temp_point.setX(this.vector_tool.X / this.scale.getX());
		temp_point.setY(this.vector_tool.Y / this.scale.getY());

		// vector_tool.X = temp_point.getX();
		// vector_tool.Y = temp_point.getY();
		// vector_tool.XYtoAR();
		// vector_tool.A = vector_tool.A +
		// this.position.getRotation().toRadians();
		// vector_tool.ARtoXY();
		// temp_point.setX(vector_tool.X + position.getX());
		// temp_point.setY(vector_tool.Y + position.getY());

	}

	// final private Point offset = Geometry.newPoint();

	@Override
	public void setOffset (final double x, final double y) {
		this.position.setXY(x, y);
		// need_setup = true;
	}

	@Override
	public void setOffset (final ReadOnlyFloat2 offset) {
		this.position.set(offset);
		// need_setup = true;
	}

	@Override
	public void setOffsetX (final double x) {
		// need_setup = true;
		this.position.setX(x);
	}

	@Override
	public void setOffsetY (final double y) {
		// need_setup = true;
		this.position.setY(y);
	}

	@Override
	public ReadOnlyFloat2 getOffset () {

		return this.position;
	}

	@Override
	public double getOffsetX () {
		return this.position.getX();
	}

	@Override
	public double getOffsetY () {
		return this.position.getY();
	}

	@Override
	public void setRotation (final Angle rotation) {
		// need_setup = true;
		this.position.getRotation().setValue(rotation);
	}

	@Override
	public void setRotation (final double radians) {
		// need_setup = true;
		this.position.getRotation().setValue(radians);
	}

	@Override
	public Angle getRotation () {

		return this.position.getRotation();
	}

	@Override
	public void setSkew (final double skewx, final double skewy) {
		// need_setup = true;
		this.skew.setXY(skewx, skewy);
	}

	@Override
	public void setSkewX (final double skew) {
		// need_setup = true;
		this.skew.setX(skew);
	}

	@Override
	public void setSkewY (final double skew) {
		// need_setup = true;
		this.skew.setY(skew);
	}

	@Override
	public void setSkew (final ReadOnlyFloat2 skew) {
		// need_setup = true;
		this.skew.set(skew);
	}

	@Override
	public ReadOnlyFloat2 getSkew () {

		return this.skew;
	}

	@Override
	public double getSkewX () {
		return this.skew.getX();
	}

	@Override
	public double getSkewY () {
		return this.skew.getY();
	}

	//

	@Override
	public void setScale (final double scalex, final double scaley) {
		// need_setup = true;
		this.scale.setXY(scalex, scaley);
	}

	@Override
	public void setScaleX (final double scale) {
		// need_setup = true;
		this.scale.setX(scale);
	}

	@Override
	public void setScaleY (final double scale) {
		// need_setup = true;
		this.scale.setY(scale);
	}

	@Override
	public void setScale (final ReadOnlyFloat2 scale) {
		// need_setup = true;
		this.scale.set(scale);
	}

	@Override
	public ReadOnlyFloat2 getScale () {

		return this.scale;
	}

	@Override
	public double getScaleX () {
		return this.scale.getX();
	}

	@Override
	public double getScaleY () {
		return this.scale.getY();
	}

	// @Override
	// public CanvasPosition getPosition() {
	// need_setup = true;
	// return this.position;
	// }
}
