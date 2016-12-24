
package com.jfixby.scarabei.api.graphs;

public interface PathState<VertexType, EdgeType> {

	Vertex<VertexType> getVertex ();

	PathStep<VertexType, EdgeType> getLeftEdge ();

	PathStep<VertexType, EdgeType> getRightEdge ();

}
