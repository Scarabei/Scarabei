package com.jfixby.red.geometry;

import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.CanvasTransform;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.math.Angle;
import com.jfixby.cmns.api.math.MathTools;
import com.jfixby.cmns.api.math.Matrix;

public class RedTransform implements CanvasTransform {

	private Matrix scale_matrix;
	private Matrix rotation_matrix;
	private Matrix offset_matrix;
	private Matrix skew_matrix;

	private Matrix un_scale_matrix;
	private Matrix un_rotation_matrix;
	private Matrix un_offset_matrix;
	private Matrix un_skew_matrix;

	private boolean need_setup = true;;

	RedTransform() {
		scale_matrix = MathTools.newIdentityMatrix(3);
		skew_matrix = MathTools.newIdentityMatrix(3);
		rotation_matrix = MathTools.newIdentityMatrix(3);
		offset_matrix = MathTools.newIdentityMatrix(3);

		un_scale_matrix = MathTools.newIdentityMatrix(3);
		un_skew_matrix = MathTools.newIdentityMatrix(3);
		un_rotation_matrix = MathTools.newIdentityMatrix(3);
		un_offset_matrix = MathTools.newIdentityMatrix(3);
	}

	@Override
	public void transform(Float2 temp_point) {

		setup();

		Geometry.applyTransformation(scale_matrix, temp_point);
		Geometry.applyTransformation(skew_matrix, temp_point);
		Geometry.applyTransformation(rotation_matrix, temp_point);
		Geometry.applyTransformation(offset_matrix, temp_point);

	}

	private void setup() {
		if (!need_setup) {
			return;
		}
		boolean log = !true;

		MathTools.setupScaleMatrix(scale_matrix, this.getScaleX(),
				this.getScaleY(), 1d);
		if (log)
			scale_matrix.print("scale_matrix");
		MathTools.setupRotationMatrix(rotation_matrix, this.position
				.getRotation().toRadians());
		if (log)
			rotation_matrix.print("rotation_matrix");
		MathTools.setupOffsetMatrix(offset_matrix, this.position.getX(),
				this.position.getY());
		if (log)
			offset_matrix.print("offset_matrix");
		MathTools
				.setupSkewMatrix(skew_matrix, this.getSkewX(), this.getSkewY());
		if (log)
			skew_matrix.print("skew_matrix");

		MathTools.inverse(scale_matrix, un_scale_matrix);
		MathTools.inverse(rotation_matrix, un_rotation_matrix);
		MathTools.inverse(offset_matrix, un_offset_matrix);
		MathTools.inverse(skew_matrix, un_skew_matrix);

		need_setup = false;
	}

	@Override
	public void reverse(Float2 temp_point) {
		setup();
		Geometry.applyTransformation(un_offset_matrix, temp_point);
		Geometry.applyTransformation(un_rotation_matrix, temp_point);
		Geometry.applyTransformation(un_skew_matrix, temp_point);
		Geometry.applyTransformation(un_scale_matrix, temp_point);

	}

	// final private Point offset = Geometry.newPoint();
	final private Float2 scale = Geometry.newFloat2(1, 1);
	final private Float2 skew = Geometry.newFloat2();
	// final private CustomAngle rotation = Angles.newAngle();
	private RedPosition position = new RedPosition();

	@Override
	public void setOffset(double x, double y) {
		this.position.setXY(x, y);
		need_setup = true;
	}

	@Override
	public void setOffset(FixedFloat2 offset) {
		this.position.set(offset);
		need_setup = true;
	}

	@Override
	public void setOffsetX(double x) {
		need_setup = true;
		this.position.setX(x);
	}

	@Override
	public void setOffsetY(double y) {
		need_setup = true;
		this.position.setY(y);
	}

	@Override
	public FixedFloat2 getOffset() {

		return this.position;
	}

	@Override
	public double getOffsetX() {
		return this.position.getX();
	}

	@Override
	public double getOffsetY() {
		return this.position.getY();
	}

	@Override
	public void setRotation(Angle rotation) {
		need_setup = true;
		this.position.getRotation().setValue(rotation);
	}

	@Override
	public void setRotation(double radians) {
		need_setup = true;
		this.position.getRotation().setValue(radians);
	}

	@Override
	public Angle getRotation() {

		return this.position.getRotation();
	}

	@Override
	public void setSkew(double skewx, double skewy) {
		need_setup = true;
		this.skew.setXY(skewx, skewy);
	}

	@Override
	public void setSkewX(double skew) {
		need_setup = true;
		this.skew.setX(skew);
	}

	@Override
	public void setSkewY(double skew) {
		need_setup = true;
		this.skew.setY(skew);
	}

	@Override
	public void setSkew(FixedFloat2 skew) {
		need_setup = true;
		this.skew.set(skew);
	}

	@Override
	public FixedFloat2 getSkew() {

		return this.skew;
	}

	@Override
	public double getSkewX() {
		return this.skew.getX();
	}

	@Override
	public double getSkewY() {
		return this.skew.getY();
	}

	//

	@Override
	public void setScale(double scalex, double scaley) {
		need_setup = true;
		this.scale.setXY(scalex, scaley);
	}

	@Override
	public void setScaleX(double scale) {
		need_setup = true;
		this.scale.setX(scale);
	}

	@Override
	public void setScaleY(double scale) {
		need_setup = true;
		this.scale.setY(scale);
	}

	@Override
	public void setScale(FixedFloat2 scale) {
		need_setup = true;
		this.scale.set(scale);
	}

	@Override
	public FixedFloat2 getScale() {

		return this.scale;
	}

	@Override
	public double getScaleX() {
		return this.scale.getX();
	}

	@Override
	public double getScaleY() {
		return this.scale.getY();
	}

	// @Override
	// public CanvasPosition getPosition() {
	// need_setup = true;
	// return this.position;
	// }
}
