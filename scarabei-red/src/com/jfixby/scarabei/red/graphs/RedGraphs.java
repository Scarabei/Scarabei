
package com.jfixby.scarabei.red.graphs;

import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.graphs.GraphsComponent;
import com.jfixby.scarabei.api.graphs.MultiGraph;
import com.jfixby.scarabei.api.graphs.PolyGraph;

public class RedGraphs implements GraphsComponent {

	@Override
	public <VertexType, EdgeType> MultiGraph<VertexType, EdgeType> newUndirectedGraph () {
		return new MultiGraphImpl<VertexType, EdgeType>();
	}

	@Override
	public <EdgeType> PolyGraph<EdgeType> newPolyGraph (EditableCollection<? extends ReadOnlyFloat2> vertices) {
		return Ploy2DGraph.newPloy2DGraph(vertices);
	}
}
