
package com.jfixby.scarabei.api.geometry.projections;

import com.jfixby.scarabei.api.floatn.Float2;

public interface RectangleTransform extends CanvasTransform {

	void setPosition (double off_x, double off_y);

	void setSize (double w, double h);

	void toAbsolute (Float2 tmp_vec);

	void toRelative (Float2 tmp_vec);

}
