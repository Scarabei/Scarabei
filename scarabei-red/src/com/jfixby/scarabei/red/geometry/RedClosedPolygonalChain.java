
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.ClosedPolygonalChain;
import com.jfixby.scarabei.api.geometry.PolyTriangulation;
import com.jfixby.scarabei.api.geometry.Triangle;
import com.jfixby.scarabei.api.geometry.Vertex;

public class RedClosedPolygonalChain extends VertexMaster implements ClosedPolygonalChain {
	// final List<RedPoint> vertices = JUtils.newList();
	final List<Vertex> public_vertices = Collections.newList();
	final RedPolyTriangulation triangulation = new RedPolyTriangulation();

	@Override
	public int size () {
		return this.public_vertices.size();
	}

	@Override
	public Vertex getVertex (final int index) {
		return this.public_vertices.getElementAt(index);
	}

	@Override
	public void clear () {
		// vertices.clear();
		this.public_vertices.clear();
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
	public void addVertex (final ReadOnlyFloat2 vertex) {
		final int shift = this.public_vertices.size();
		final int new_size = 1 + shift;
		this.setSize(new_size);
		this.public_vertices.getElementAt(shift).relative().set(vertex);
	}

	@Override
	public void setSize (final int n) {
		if (n < 0) {
			Err.reportError("Negative size: " + n);
		}
		if (n == 0) {
			this.public_vertices.clear();
			return;
		}
		int current_size = this.public_vertices.size();
		if (current_size == n) {
			return;
		}
		if (current_size < n) {
			do {
				current_size = this.addVertex(current_size);
			} while (current_size < n);
		} else {
			do {
				current_size = this.removeVertex(current_size);
			} while (current_size > n);
		}
	}

	private int addVertex (final int current_size) {
		if (current_size == 0) {
			final RedVertex new_vertex = this.newVertex();
			final RedEdge new_edge = new RedEdge();
			new_edge.setLeftVertex(new_vertex);
			new_edge.setRightVertex(new_vertex);
			new_vertex.setRightEdge(new_edge);
			new_vertex.setLeftEdge(new_edge);
			this.public_vertices.add(new_vertex);
			return 1;
		}

		final RedVertex first = (RedVertex)this.public_vertices.getElementAt(0);
		final RedVertex last = (RedVertex)this.public_vertices.getElementAt(current_size - 1);

		final RedVertex new_vertex = this.newVertex();
		final RedEdge new_edge = new RedEdge();

		last.setRightEdge(new_edge);
		new_edge.setLeftVertex(last);

		new_edge.setRightVertex(new_vertex);
		new_vertex.setLeftEdge(new_edge);

		final RedEdge first_left_edge = first.getLeftEdge();
		new_vertex.setRightEdge(first_left_edge);
		first_left_edge.setLeftVertex(new_vertex);

		this.public_vertices.add(new_vertex);

		return this.public_vertices.size();

	}

	private int removeVertex (final int current_size) {// current_size>1;
		final RedVertex removed = (RedVertex)this.public_vertices.removeElementAt(current_size - 1);

		final RedVertex first = (RedVertex)this.public_vertices.getElementAt(0);
		final RedVertex last = (RedVertex)this.public_vertices.getElementAt(current_size - 2);

		final RedEdge edge = first.getLeftEdge();
		edge.setLeftVertex(last);
		last.setRightEdge(edge);

		final RedEdge removed_left_edge = removed.getLeftEdge();
		removed.setLeftEdge(null);
		removed.setRightEdge(null);
		removed_left_edge.setLeftVertex(null);
		removed_left_edge.setRightVertex(null);

		return this.public_vertices.size();
	}

	private RedVertex newVertex () {
		return new RedVertex(this);
	}

	@Override
	public PolyTriangulation getTriangulation () {
		return this.triangulation.check(this.public_vertices);
	}

	@Override
	public Collection<Vertex> listVertices () {
		return this.public_vertices;
	}

	@Override
	public void print (final String tag) {
		this.public_vertices.print(tag);
	}

	@Override
	public boolean containsPoint (final double point_x, final double point_y) {
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
	public void setupVertices (final Collection<Float2> input) {
		Debug.checkNull("input", input);
		this.setSize(input.size());
		for (int i = 0; i < input.size(); i++) {
			this.getVertex(i).relative().set(input.getElementAt(i));
		}

	}

}
