package com.jfixby.cmns.api.graphs;

import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.floatn.FixedFloat2;

public interface GraphsComponent {

	<VertexType, EdgeType> MultiGraph<VertexType, EdgeType> newUndirectedGraph();

	public <EdgeType> PolyGraph<EdgeType> newPolyGraph(EditableCollection<? extends FixedFloat2> vertices);

}
