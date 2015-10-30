package com.jfixby.cmns.api.math;

import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.Triangle;

public interface SimpleTriangulatorComponent {

	List<Triangle> triangulate(EditableCollection<Float2> vertices_list);

}
