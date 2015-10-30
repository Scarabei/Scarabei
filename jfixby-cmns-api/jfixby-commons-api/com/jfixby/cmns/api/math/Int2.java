package com.jfixby.cmns.api.math;

public interface Int2 extends FixedInt2 {

	Int2 setX(long x);

	Int2 setY(long y);

	Int2 setXY(FixedInt2 other);

	Int2 setXY(long x, long y);

}
