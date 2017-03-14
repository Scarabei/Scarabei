
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.PolyTriangulation;
import com.jfixby.scarabei.api.geometry.Triangle;
import com.jfixby.scarabei.api.geometry.Vertex;
import com.jfixby.scarabei.api.graphs.Graphs;
import com.jfixby.scarabei.api.graphs.PathInGraph;
import com.jfixby.scarabei.api.graphs.PolyGraph;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.SimpleTriangulator;

public class RedPolyTriangulation implements PolyTriangulation {
	final List<RedPoint> triangulated_vertices = Collections.newList();
	final List<Triangle> triangles = Collections.newList();
	final List<ReadOnlyFloat2> dots = Collections.newList();

	public PolyTriangulation check (List<Vertex> vertices) {
		final boolean already_triangulated = Geometry.equalFloat2Collections(vertices, this.triangulated_vertices);
		if (!already_triangulated) {
			L.d("retriangulating...");
			// L.d("eq", vertices.equals(triangulated_vertices));
			Geometry.setPointsCollectionSize(this.triangulated_vertices, vertices.size());
			Geometry.copyValues(vertices, this.triangulated_vertices);
			// vertices.print("vertices");
			// triangulated_vertices.print("triangulated_vertices");
			// L.d("eq", vertices.equals(triangulated_vertices));
			triangulate();
		}
		return this;
	}

	@Override
	public int size () {
		return triangles.size();
	}

	private void triangulate () {
		this.triangles.clear();
		this.dots.clear();
		PolyGraph<Object> graph = Graphs.newPolyGraph(triangulated_vertices);
		List<PathInGraph<Float2, Object>> cycles_list = graph.extractSimpleCycles();
		for (int i = 0; i < cycles_list.size(); i++) {
			collectTriangles(cycles_list.getElementAt(i));
		}
	}

	private void collectTriangles (PathInGraph<Float2, Object> pathInGraph) {
		List<Float2> vertices_list = pathInGraph.toVerticesList();
		List<Triangle> triangles = SimpleTriangulator.triangulate(vertices_list);
		this.triangles.addAll(triangles);
		for (int i = 0; i < triangles.size(); i++) {
			Triangle triangle = triangles.getElementAt(i);
			this.dots.add(triangle.A().transformed());
			this.dots.add(triangle.B().transformed());
			this.dots.add(triangle.C().transformed());
		}
	}

	@Override
	public Triangle getTriangle (int i) {
		return triangles.getElementAt(i);
	}

	@Override
	public EditableCollection<ReadOnlyFloat2> asDots () {
		return dots;
	}
}
