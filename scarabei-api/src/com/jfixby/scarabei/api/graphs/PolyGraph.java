
package com.jfixby.scarabei.api.graphs;

import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.Float2;

public interface PolyGraph<EdgeType> extends MultiGraph<Float2, EdgeType> {

	List<PathInGraph<Float2, EdgeType>> extractSimpleCycles ();

}
