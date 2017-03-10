
package com.jfixby.scarabei.api.graphs;

public interface MultiGraph<VertexType, EdgeType> {

	Vertex<VertexType> newVertex ();

	Vertex<VertexType> findVertexByObject (VertexType vertex_object);

	Edge<EdgeType> newEdge (Vertex<VertexType> vertex_a, Vertex<VertexType> vertex_b);

	PathInGraph<VertexType, EdgeType> findPath (Vertex<VertexType> from_vertex, Vertex<VertexType> to_vertex);

	void print (String tag);

}
