
package com.jfixby.scarabei.api.math;

import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.Triangle;

public interface SimpleTriangulatorComponent {

	List<Triangle> triangulate (EditableCollection<Float2> vertices_list);

}
