
package com.jfixby.scarabei.api.geometry.projections;

import com.jfixby.scarabei.api.floatn.Float2;

public interface Transform2D {

	void transform (Float2 temp_point);

	void reverse (Float2 temp_point);

}
