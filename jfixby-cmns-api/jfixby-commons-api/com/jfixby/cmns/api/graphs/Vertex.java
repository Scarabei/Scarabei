package com.jfixby.cmns.api.graphs;

public interface Vertex<VertexType> {

	void putVertexObject(VertexType object);

	VertexType getVertexObject();

}
