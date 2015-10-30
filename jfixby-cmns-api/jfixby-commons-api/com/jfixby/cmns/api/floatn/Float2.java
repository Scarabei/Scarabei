package com.jfixby.cmns.api.floatn;

public interface Float2 extends FixedFloat2 {

	Float2 setXY(double x, double y);

	Float2 setX(double x);

	Float2 setY(double y);

	Float2 set(FixedFloat2 other);

	Float2 setXY();

	Float2 add(FixedFloat2 offset);

	Float2 addX(double delta);

	Float2 addY(double delta);

	Float2 add(double deltaX, double deltaY);

	void scaleXY(double factor);

	void scaleXY(double x, double y);

	void scaleXY(Float2 other);

	double product(Float2 other);

}
