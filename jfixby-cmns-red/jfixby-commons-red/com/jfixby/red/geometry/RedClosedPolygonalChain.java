package com.jfixby.red.geometry;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.ClosedPolygonalChain;
import com.jfixby.cmns.api.geometry.PolyTriangulation;
import com.jfixby.cmns.api.geometry.Triangle;
import com.jfixby.cmns.api.geometry.Vertex;

public class RedClosedPolygonalChain extends VertexMaster implements
		ClosedPolygonalChain {
	// final List<RedPoint> vertices = JUtils.newList();
	final List<Vertex> public_vertices = JUtils.newList();
	final RedPolyTriangulation triangulation = new RedPolyTriangulation();

	@Override
	public int size() {
		return public_vertices.size();
	}

	@Override
	public Vertex getVertex(int index) {
		return public_vertices.getElementAt(index);
	}

	@Override
	public void clear() {
		// vertices.clear();
		public_vertices.clear();
	}

	// @Override
	// public void setup(Collection<? extends Dot> vertices) {
	// this.setSize(vertices.size());
	// Geometry.copyValues(vertices, this.public_vertices);
	// // public_vertices.clear();
	// // public_vertices.addAll(this.vertices);
	// }
	//
	// @Override
	// public void addVertices(Collection<? extends Dot> vertices) {
	// int offset = this.public_vertices.size();
	// int new_size = vertices.size() + offset;
	// this.setSize(new_size);
	// Geometry.copyValues(vertices, this.public_vertices, offset);
	// }
	//
	@Override
	public void addVertex(FixedFloat2 vertex) {
		int shift = this.public_vertices.size();
		int new_size = 1 + shift;
		this.setSize(new_size);
		this.public_vertices.getElementAt(shift).relative().set(vertex);
	}

	@Override
	public void setSize(int n) {
		if (n < 0) {
			throw new Error("Negative size: " + n);
		}
		if (n == 0) {
			public_vertices.clear();
			return;
		}
		int current_size = public_vertices.size();
		if (current_size == n) {
			return;
		}
		if (current_size < n) {
			do {
				current_size = addVertex(current_size);
			} while (current_size < n);
		} else {
			do {
				current_size = removeVertex(current_size);
			} while (current_size > n);
		}
	}

	private int addVertex(final int current_size) {
		if (current_size == 0) {
			final RedVertex new_vertex = newVertex();
			final RedEdge new_edge = new RedEdge();
			new_edge.setLeftVertex(new_vertex);
			new_edge.setRightVertex(new_vertex);
			new_vertex.setRightEdge(new_edge);
			new_vertex.setLeftEdge(new_edge);
			public_vertices.add(new_vertex);
			return 1;
		}

		RedVertex first = (RedVertex) this.public_vertices.getElementAt(0);
		RedVertex last = (RedVertex) this.public_vertices
				.getElementAt(current_size - 1);

		RedVertex new_vertex = newVertex();
		final RedEdge new_edge = new RedEdge();

		last.setRightEdge(new_edge);
		new_edge.setLeftVertex(last);

		new_edge.setRightVertex(new_vertex);
		new_vertex.setLeftEdge(new_edge);

		RedEdge first_left_edge = first.getLeftEdge();
		new_vertex.setRightEdge(first_left_edge);
		first_left_edge.setLeftVertex(new_vertex);

		public_vertices.add(new_vertex);

		return this.public_vertices.size();

	}

	private int removeVertex(final int current_size) {// current_size>1;
		RedVertex removed = (RedVertex) this.public_vertices
				.removeElementAt(current_size - 1);

		RedVertex first = (RedVertex) this.public_vertices.getElementAt(0);
		RedVertex last = (RedVertex) this.public_vertices
				.getElementAt(current_size - 2);

		RedEdge edge = first.getLeftEdge();
		edge.setLeftVertex(last);
		last.setRightEdge(edge);

		RedEdge removed_left_edge = removed.getLeftEdge();
		removed.setLeftEdge(null);
		removed.setRightEdge(null);
		removed_left_edge.setLeftVertex(null);
		removed_left_edge.setRightVertex(null);

		return this.public_vertices.size();
	}

	private RedVertex newVertex() {
		return new RedVertex(this);
	}

	@Override
	public PolyTriangulation getTriangulation() {
		return this.triangulation.check(this.public_vertices);
	}

	@Override
	public Collection<Vertex> listVertices() {
		return this.public_vertices;
	}

	@Override
	public void print(String tag) {
		public_vertices.print(tag);
	}

	@Override
	public boolean containsPoint(double point_x, double point_y) {
		final PolyTriangulation triangles = this.getTriangulation();
		for (int i = 0; i < triangles.size(); i++) {
			final Triangle triangle = triangles.getTriangle(i);
			if (triangle.containsPoint(point_y, point_y)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setupVertices(Collection<Float2> input) {
		JUtils.checkNull("input", input);
		this.setSize(input.size());
		for (int i = 0; i < input.size(); i++) {
			this.getVertex(i).relative().set(input.getElementAt(i));
		}

	}

}
