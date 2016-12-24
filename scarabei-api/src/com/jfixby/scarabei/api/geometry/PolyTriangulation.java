
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.floatn.FixedFloat2;

public interface PolyTriangulation {

	int size ();

	Triangle getTriangle (int i);

	EditableCollection<FixedFloat2> asDots ();

}
