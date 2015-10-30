package com.jfixby.red.geometry;

import com.jfixby.cmns.api.geometry.Edge;
import com.jfixby.cmns.api.geometry.Vertex;

public class RedEdge implements Edge {

	RedVertex left_vertex;
	RedVertex right_vertex;

	@Override
	public Vertex getLeftVertex() {
		return left_vertex;
	}

	@Override
	public Vertex getRightVertex() {
		return right_vertex;
	}

	public void setLeftVertex(RedVertex new_vertex) {
		this.left_vertex = new_vertex;
	}

	public void setRightVertex(RedVertex new_vertex) {
		this.right_vertex = new_vertex;
	}

}
