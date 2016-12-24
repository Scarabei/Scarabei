
package com.jfixby.scarabei.red.graphs;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.graphs.Vertex;

public class VertexImpl<VertexType, EdgeType> implements Vertex<VertexType> {

	@Override
	public String toString () {
		return "Vertex(" + v_type + ")";
	}

	VertexType v_type;

	final private Set<EdgeImpl<VertexType, EdgeType>> edges = Collections.newSet();

	public VertexImpl (VertexType t) {
		this.putVertexObject(t);
	}

	public VertexImpl () {

	}

	void addLink (EdgeImpl<VertexType, EdgeType> e) {
		edges.add(e);
	}

	Set<EdgeImpl<VertexType, EdgeType>> getLinks () {
		return edges;
	}

	@Override
	public VertexType getVertexObject () {
		return this.v_type;
	}

	@Override
	public void putVertexObject (VertexType object) {
		this.v_type = object;
	}

}
