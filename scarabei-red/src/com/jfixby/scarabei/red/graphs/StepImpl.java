
package com.jfixby.scarabei.red.graphs;

import com.jfixby.scarabei.api.graphs.PathStep;

public class StepImpl<VertexType, EdgeType> implements PathStep<VertexType, EdgeType> {

	StateImpl<VertexType, EdgeType> rightStep;
	EdgeImpl<VertexType, EdgeType> edge;
	StateImpl<VertexType, EdgeType> leftStep;

	public void setLeftState (StateImpl<VertexType, EdgeType> leftStep) {
		this.leftStep = leftStep;
	}

	public void setRightState (StateImpl<VertexType, EdgeType> rightStep) {
		this.rightStep = rightStep;
	}

	public EdgeImpl<VertexType, EdgeType> getEdge () {
		return edge;
	}

	public void setEdge (EdgeImpl<VertexType, EdgeType> edge) {
		this.edge = edge;
	}

	public StateImpl<VertexType, EdgeType> getRightState () {
		return rightStep;
	}

	public StateImpl<VertexType, EdgeType> getLeftState () {
		return leftStep;
	}
}
