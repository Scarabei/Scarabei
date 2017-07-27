
package com.jfixby.scarabei.red.graphs;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.graphs.Edge;

public class EdgeImpl<VertexType, EdgeType> implements Edge<EdgeType> {
	private VertexImpl<VertexType, EdgeType> rightNode;
	private VertexImpl<VertexType, EdgeType> leftNode;

	public VertexImpl<VertexType, EdgeType> getRightNode () {
		return this.rightNode;
	}

	public void setRightNode (final VertexImpl<VertexType, EdgeType> rightNode) {
		this.rightNode = rightNode;
	}

	public VertexImpl<VertexType, EdgeType> getLeftNode () {
		return this.leftNode;
	}

	public void setLeftNode (final VertexImpl<VertexType, EdgeType> leftNode) {
		this.leftNode = leftNode;
	}

	public void setObject (final EdgeType object) {
		this.object = object;
	}

	@Override
	public String toString () {
		return "Edge [" + this.leftNode.getVertexObject() + "]-[" + this.rightNode.getVertexObject() + "] @ " + this.object;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}

		return false;
	}

	public boolean isNeighbour (final EdgeImpl<VertexType, EdgeType> other) {
		return this.rightNode == other.rightNode || //
			this.rightNode == other.leftNode || //
			this.leftNode == other.rightNode || //
			this.leftNode == other.leftNode //
		;
	}

	public VertexImpl<VertexType, EdgeType> getOtherNode (final VertexImpl<VertexType, EdgeType> champion) {
		if (champion == this.leftNode) {
			return this.rightNode;
		}
		if (champion == this.rightNode) {
			return this.leftNode;
		}

		Err.reportError("Node " + champion + " does not belong here. (" + this + ")");
		return champion;
	}

	public void switchDirection () {
		final VertexImpl<VertexType, EdgeType> tmp = this.rightNode;
		this.rightNode = this.leftNode;
		this.leftNode = tmp;

	}

	public EdgeImpl (final EdgeImpl<VertexType, EdgeType> start_direction) {
		this.rightNode = start_direction.rightNode;
		this.leftNode = start_direction.leftNode;
	}

	public EdgeImpl () {
		// TODO Auto-generated constructor stub
	}

	EdgeType object;

	@Override
	public void putObject (final EdgeType object) {
		this.object = object;
	}

	@Override
	public EdgeType getObject () {
		return this.object;
	}
}
