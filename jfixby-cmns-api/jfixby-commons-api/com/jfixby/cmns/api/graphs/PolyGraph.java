package com.jfixby.cmns.api.graphs;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.floatn.Float2;

public interface PolyGraph<EdgeType> extends MultiGraph<Float2, EdgeType> {

	List<PathInGraph<Float2, EdgeType>> extractSimpleCycles();

}
