package com.jfixby.cmns.api.transform;

import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.CanvasTransform;

public interface RectangleTransform extends CanvasTransform {

	void setPosition(double off_x, double off_y);

	void setSize(double w, double h);

	void toAbsolute(Float2 tmp_vec);

	void toRelative(Float2 tmp_vec);

}
