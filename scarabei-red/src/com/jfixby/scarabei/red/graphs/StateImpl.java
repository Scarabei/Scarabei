
package com.jfixby.scarabei.red.graphs;

import com.jfixby.scarabei.api.graphs.PathState;

public class StateImpl<VertexType, EdgeType> implements PathState<VertexType, EdgeType> {

	VertexImpl<VertexType, EdgeType> vertex;

	@Override
	public VertexImpl<VertexType, EdgeType> getVertex () {
		return vertex;
	}

	public StateImpl<VertexType, EdgeType> setVertex (VertexImpl<VertexType, EdgeType> vertex) {
		this.vertex = vertex;
		return this;
	}

	private StepImpl<VertexType, EdgeType> rightEdge;
	private StepImpl<VertexType, EdgeType> leftEdge;

	// private VertexType vertex_object;

	@Override
	public StepImpl<VertexType, EdgeType> getLeftEdge () {
		return leftEdge;
	}

	@Override
	public StepImpl<VertexType, EdgeType> getRightEdge () {
		return rightEdge;
	}

	void setRightEdge (StepImpl<VertexType, EdgeType> rightEdge) {
		this.rightEdge = rightEdge;
	}

	void setLeftEdge (StepImpl<VertexType, EdgeType> leftEdge) {
		this.leftEdge = leftEdge;
	}

}
