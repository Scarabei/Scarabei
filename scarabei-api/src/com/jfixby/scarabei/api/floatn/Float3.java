
package com.jfixby.scarabei.api.floatn;

public interface Float3 extends ReadOnlyFloat3 {

	void setXYZ (double x, double y, double z);

	void setXYZ (Float3 other);

	void setZ (double z);

	void setX (double x);

	void setY (double y);
}
