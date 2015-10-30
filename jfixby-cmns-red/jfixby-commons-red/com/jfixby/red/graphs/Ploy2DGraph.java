package com.jfixby.red.graphs;

import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.graphs.PathInGraph;
import com.jfixby.cmns.api.graphs.PolyGraph;

public class Ploy2DGraph<EdgeType> extends MultiGraphImpl<Float2, EdgeType> implements PolyGraph<EdgeType> {

	public Ploy2DGraph() {
		super();
	}

	@Override
	public List<PathInGraph<Float2, EdgeType>> extractSimpleCycles() {
		List<PathInGraph<Float2, EdgeType>> cycles = PolyGraphUtils.extractSimpleCycles(this);
		return cycles;
	}

	public static <EdgeType> PolyGraph<EdgeType> newPloy2DGraph(EditableCollection<? extends FixedFloat2> points) {
		Ploy2DGraph<EdgeType> returl = PolyGraphUtils.newMultiGraph(points);
		return returl;
	}

	

}
