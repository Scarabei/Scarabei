package com.jfixby.red.graphs;

import com.jfixby.cmns.api.graphs.Edge;

public  class EdgeImpl<VertexType, EdgeType> implements Edge<EdgeType> {
	private VertexImpl<VertexType, EdgeType> rightNode;
	private VertexImpl<VertexType, EdgeType> leftNode;

	public VertexImpl<VertexType, EdgeType> getRightNode() {
		return rightNode;
	}

	public void setRightNode(VertexImpl<VertexType, EdgeType> rightNode) {
		this.rightNode = rightNode;
	}

	public VertexImpl<VertexType, EdgeType> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(VertexImpl<VertexType, EdgeType> leftNode) {
		this.leftNode = leftNode;
	}

	public void setObject(EdgeType object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "Edge [" + leftNode.getVertexObject() + "]-[" + rightNode.getVertexObject()
				+ "] @ " + this.object;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		return false;
	}

	public boolean isNeighbour(EdgeImpl<VertexType, EdgeType> other) {
		return rightNode == other.rightNode || //
				rightNode == other.leftNode || //
				leftNode == other.rightNode || //
				leftNode == other.leftNode //
		;
	}

	public VertexImpl<VertexType, EdgeType> getOtherNode(
			VertexImpl<VertexType, EdgeType> champion) {
		if (champion == this.leftNode) {
			return this.rightNode;
		}
		if (champion == this.rightNode) {
			return this.leftNode;
		}

		throw new Error("Node " + champion + " does not belong here. (" + this
				+ ")");
	}

	public void switchDirection() {
		final VertexImpl<VertexType, EdgeType> tmp = this.rightNode;
		this.rightNode = this.leftNode;
		this.leftNode = tmp;

	}

	public EdgeImpl(EdgeImpl<VertexType, EdgeType> start_direction) {
		this.rightNode = start_direction.rightNode;
		this.leftNode = start_direction.leftNode;
	}

	public EdgeImpl() {
		// TODO Auto-generated constructor stub
	}

	EdgeType object;

	@Override
	public void putObject(EdgeType object) {
		this.object = object;
	}

	@Override
	public EdgeType getObject() {
		return object;
	}
}
