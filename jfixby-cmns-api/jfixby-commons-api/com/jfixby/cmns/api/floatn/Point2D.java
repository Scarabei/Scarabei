package com.jfixby.cmns.api.floatn;

public interface Point2D extends FixedFloat2 {

	Point2D setX(double x);

	Point2D setY(double x);

	Point2D shiftX(double delta);

	Point2D shiftY(double delta);

}
