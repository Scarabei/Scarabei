
package com.jfixby.scarabei.api.floatn;

public interface Float2 extends ReadOnlyFloat2 {

	Float2 setXY (double x, double y);

	Float2 setX (double x);

	Float2 setY (double y);

	Float2 set (ReadOnlyFloat2 other);

	Float2 setXY ();

	Float2 add (ReadOnlyFloat2 offset);

	Float2 addX (double delta);

	Float2 addY (double delta);

	Float2 add (double deltaX, double deltaY);

	Float2 scaleXY (double factor);

	Float2 scaleXY (double x, double y);

	Float2 scaleXY (Float2 other);

	double product (Float2 other);

	Float2 setLinearSum (ReadOnlyFloat2 a, double alpha, ReadOnlyFloat2 b, double betta);

	void subtract (ReadOnlyFloat2 position);

}
