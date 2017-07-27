
package com.jfixby.scarabei.red.graphs;

import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.graphs.PathInGraph;
import com.jfixby.scarabei.api.graphs.PolyGraph;

public class Ploy2DGraph<EdgeType> extends MultiGraphImpl<Float2, EdgeType> implements PolyGraph<EdgeType> {

	public Ploy2DGraph () {
		super();
	}

	@Override
	public List<PathInGraph<Float2, EdgeType>> extractSimpleCycles () {
		List<PathInGraph<Float2, EdgeType>> cycles = PolyGraphUtils.extractSimpleCycles(this);
		return cycles;
	}

	public static <EdgeType> PolyGraph<EdgeType> newPloy2DGraph (EditableCollection<? extends ReadOnlyFloat2> points) {
		Ploy2DGraph<EdgeType> returl = PolyGraphUtils.newMultiGraph(points);
		return returl;
	}

}
