
package com.jfixby.scarabei.api.transform;

import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.CanvasTransform;

public interface RectangleTransform extends CanvasTransform {

	void setPosition (double off_x, double off_y);

	void setSize (double w, double h);

	void toAbsolute (Float2 tmp_vec);

	void toRelative (Float2 tmp_vec);

}
