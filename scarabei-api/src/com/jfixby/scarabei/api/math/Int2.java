
package com.jfixby.scarabei.api.math;

public interface Int2 extends ReadOnlyInt2 {

	Int2 setX (long x);

	Int2 setY (long y);

	Int2 setXY (ReadOnlyInt2 other);

	Int2 setXY (long x, long y);

}
