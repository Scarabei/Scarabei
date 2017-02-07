
package com.jfixby.scarabei.api.graphs;

import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;

public interface GraphsComponent {

	<VertexType, EdgeType> MultiGraph<VertexType, EdgeType> newUndirectedGraph ();

	public <EdgeType> PolyGraph<EdgeType> newPolyGraph (EditableCollection<? extends ReadOnlyFloat2> vertices);

}
