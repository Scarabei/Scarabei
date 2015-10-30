package com.jfixby.cmns.api.geometry;

import com.jfixby.cmns.api.floatn.Float2;

public interface Transform {

	void transform(Float2 temp_point);

	void reverse(Float2 temp_point);

}
