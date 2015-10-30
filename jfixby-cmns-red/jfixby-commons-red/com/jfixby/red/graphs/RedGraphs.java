package com.jfixby.red.graphs;

import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.graphs.GraphsComponent;
import com.jfixby.cmns.api.graphs.MultiGraph;
import com.jfixby.cmns.api.graphs.PolyGraph;

public class RedGraphs implements GraphsComponent {

	@Override
	public <VertexType, EdgeType> MultiGraph<VertexType, EdgeType> newUndirectedGraph() {
		return new MultiGraphImpl<VertexType, EdgeType>();
	}

	@Override
	public <EdgeType> PolyGraph<EdgeType> newPolyGraph(EditableCollection<? extends FixedFloat2> vertices) {
		return Ploy2DGraph.newPloy2DGraph(vertices);
	}
}
