
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;

public interface Spline2D {

	void computeSpline (Collection<? extends ReadOnlyFloat2> animations_list, int resolution);

	void readValue (double t, Float2 result);

}
