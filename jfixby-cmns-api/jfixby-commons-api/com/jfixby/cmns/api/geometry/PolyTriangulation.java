package com.jfixby.cmns.api.geometry;

import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.floatn.FixedFloat2;

public interface PolyTriangulation {

	int size();

	Triangle getTriangle(int i);

	EditableCollection<FixedFloat2> asDots();

}
