
package com.jfixby.scarabei.red.graphs;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.graphs.Edge;
import com.jfixby.scarabei.api.graphs.MultiGraph;
import com.jfixby.scarabei.api.graphs.PathInGraph;
import com.jfixby.scarabei.api.graphs.Vertex;
import com.jfixby.scarabei.api.log.L;

public class MultiGraphImpl<VertexType, EdgeType> implements MultiGraph<VertexType, EdgeType> {

	public MultiGraphImpl () {
		super();
	}

	void print (final MultiGraphImpl<VertexType, EdgeType> graph) {
		L.d("---MultiGraph---");
		L.d("Nodes:");
		for (int i = 0; i < graph.size(); i++) {
			final VertexImpl<VertexType, EdgeType> n = graph.getVertex(i);
			L.d("   [" + i + "] " + n);
			final Set<EdgeImpl<VertexType, EdgeType>> links = n.getLinks();
			for (int k = 0; k < links.size(); k++) {
				final EdgeImpl<VertexType, EdgeType> e = links.getElementAt(k);
				L.d("       " + graph.toString(e));
			}

		}
		// printEdges("Edges", graph.edges, graph);

	}

	void printEdges (final String string, final List<EdgeImpl<VertexType, EdgeType>> list,
		final MultiGraphImpl<VertexType, EdgeType> graph) {
		L.d(string + ":");
		for (int i = 0; i < list.size(); i++) {
			final EdgeImpl<VertexType, EdgeType> n = list.getElementAt(i);

			L.d("   [" + i + "] " + graph.toString(n));
			L.d("       " + n);
		}

	}

	static void printNodes (final String string, final List<VertexImpl<Object, Object>> list,
		final MultiGraphImpl<Object, Object> graph) {
		L.d(string + ":");
		for (int i = 0; i < list.size(); i++) {
			final VertexImpl<Object, Object> n = list.getElementAt(i);

			L.d("   [" + i + "] " + graph.toString(n));
			L.d("       " + n);
		}

	}

	private String toString (final VertexImpl<Object, Object> n) {
		return n.toString();
	}

	private VertexImpl<VertexType, EdgeType> getVertex (final int i) {
		return this.vertices.getElementAt(i);
	}

	final List<VertexImpl<VertexType, EdgeType>> vertices = Collections.newList();
	final List<EdgeImpl<VertexType, EdgeType>> edges = Collections.newList();

	public int size () {
		return this.vertices.size();
	}

	public void establishLinks () {
		for (int i = 0; i < this.edges.size(); i++) {
			final EdgeImpl<VertexType, EdgeType> e = this.edges.getElementAt(i);
			e.getLeftNode().addLink(e);
			e.getRightNode().addLink(e);
		}

	}

	public int numberOfEdges () {
		return this.edges.size();
	}

	public EdgeImpl<VertexType, EdgeType> getEdge (final int i) {
		return this.edges.getElementAt(i);
	}

	private String toString (final EdgeImpl<VertexType, EdgeType> current) {
		return "[" + this.indexOf(current) + "] " + current.toString();
	}

	public int indexOf (final EdgeImpl<VertexType, EdgeType> n) {
		return this.indexOf(n, this.edges);
	}

	int indexOf (final EdgeImpl<VertexType, EdgeType> n, final List<EdgeImpl<VertexType, EdgeType>> edges) {
		for (int i = 0; i < edges.size(); i++) {
			final EdgeImpl<VertexType, EdgeType> e = edges.getElementAt(i);
			if (e == n) {
				return i;
			}
		}
		return -1;
	}

	public void print () {
		// print(this);

	}

	@Override
	public Vertex<VertexType> newVertex () {
		final VertexImpl<VertexType, EdgeType> element = new VertexImpl<VertexType, EdgeType>();
		this.vertices.add(element);
		return element;
	}

	@Override
	public Vertex<VertexType> findVertexByObject (final VertexType vertex_object) {
		if (vertex_object == null) {
			Err.reportError("Null argument exception.");
		}
		for (int i = 0; i < this.vertices.size(); i++) {
			final VertexImpl<VertexType, EdgeType> vertex = this.vertices.getElementAt(i);
			if (vertex_object == vertex.getVertexObject()) {
				// if (vertex_object.equals(vertex.getObject())) {
				return vertex;
			}
		}
		return null;
	}

	@Override
	public Edge<EdgeType> newEdge (final Vertex<VertexType> vertex_a, final Vertex<VertexType> vertex_b) {
		final EdgeImpl<VertexType, EdgeType> edge = this.createNewEdge((VertexImpl<VertexType, EdgeType>)vertex_a,
			(VertexImpl<VertexType, EdgeType>)vertex_b);

		this.establishLinks();

		return edge;
	}

	public EdgeImpl<VertexType, EdgeType> createNewEdge (final VertexImpl<VertexType, EdgeType> left_node,
		final VertexImpl<VertexType, EdgeType> right_node) {
		if (left_node == null || right_node == null) {
			Err.reportError("left_node=" + left_node + " , right_node=" + right_node);
		}
		final EdgeImpl<VertexType, EdgeType> edge = new EdgeImpl<VertexType, EdgeType>();
		edge.setLeftNode(left_node);
		edge.setRightNode(right_node);
		this.edges.add(edge);
		return edge;

	}

	@Override
	public PathInGraph<VertexType, EdgeType> findPath (final Vertex<VertexType> from_vertex, final Vertex<VertexType> to_vertex) {
		final List<VertexImpl<VertexType, EdgeType>> visited = Collections.newList();
		final List<VertexImpl<VertexType, EdgeType>> states = Collections.newList();
		final List<EdgeImpl<VertexType, EdgeType>> steps = Collections.newList();

		visited.add((VertexImpl<VertexType, EdgeType>)from_vertex);
		states.add((VertexImpl<VertexType, EdgeType>)from_vertex);
		this.try_search((VertexImpl<VertexType, EdgeType>)from_vertex, (VertexImpl<VertexType, EdgeType>)to_vertex, visited, states,
			steps);
		// L.d("--------------------------------------------------");
		// L.d("from_vertex", from_vertex);
		// L.d(" to_vertex", to_vertex);
		// states.print("states");
		// steps.print("steps");

		final PathImpl<VertexType, EdgeType> path = new PathImpl<VertexType, EdgeType>();
		path.setup(states, steps);

		// path.print("path");
		// RedTriplane.exit();
		return path;
	}

	private boolean try_search (final VertexImpl<VertexType, EdgeType> from_vertex,
		final VertexImpl<VertexType, EdgeType> final_vertex, final List<VertexImpl<VertexType, EdgeType>> visited,
		final List<VertexImpl<VertexType, EdgeType>> states, final List<EdgeImpl<VertexType, EdgeType>> steps) {
		if (from_vertex == final_vertex) {
			return true;
		}
		for (int i = 0; i < from_vertex.getLinks().size(); i++) {
			final EdgeImpl<VertexType, EdgeType> edge = from_vertex.getLinks().getElementAt(i);
			final VertexImpl<VertexType, EdgeType> next = edge.getOtherNode(from_vertex);
			if (!visited.contains(next)) {
				states.add(next);
				steps.add(edge);
				visited.add(next);
				if (this.try_search(next, final_vertex, visited, states, steps)) {
					return true;
				} else {
					states.remove(next);
					steps.remove(edge);
				}
			}
		}
		return false;
	}

	@Override
	public void print (final String tag) {
		this.print(this);
	}
}
