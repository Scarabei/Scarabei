package com.jfixby.red.graphs;

import com.jfixby.cmns.api.angles.Angles;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.graphs.PathInGraph;
import com.jfixby.cmns.api.math.CustomAngle;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.math.MathTools;
import com.jfixby.cmns.api.math.VectorTool;

public class PolyGraphUtils {
	static private <EdgeType> void removeLoopEdges(Ploy2DGraph<EdgeType> multy_graph) {
		final List<EdgeImpl<Float2, EdgeType>> edges_to_remove = JUtils.newList();

		for (int k = 0; k < multy_graph.edges.size(); k++) {
			final EdgeImpl<Float2, EdgeType> other = multy_graph.edges.getElementAt(k);
			if (other.getLeftNode() == other.getRightNode()) {
				edges_to_remove.add(other);
			}
		}

		multy_graph.edges.removeAll(edges_to_remove);
	}

	static public <EdgeType> boolean isDuplicatingEdge(final EdgeImpl<Float2, EdgeType> one,
			final EdgeImpl<Float2, EdgeType> other, Ploy2DGraph<EdgeType> multy_graph) {
		final VertexImpl<Float2, EdgeType> AL = one.getLeftNode();
		final VertexImpl<Float2, EdgeType> AR = one.getRightNode();
		final VertexImpl<Float2, EdgeType> BL = other.getLeftNode();
		final VertexImpl<Float2, EdgeType> BR = other.getRightNode();

		if (AL == BL) {
			if (AR == BR) {
				return true;
			} else {
				return false;
			}
		}

		if (AL == BR) {
			if (AR == BL) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	static private <EdgeType> void findIntersections(Ploy2DGraph<EdgeType> multy_graph) {
		final List<EdgeImpl<Float2, EdgeType>> edges_to_remove = JUtils.newList();
		final List<EdgeImpl<Float2, EdgeType>> edges_to_add = JUtils.newList();
		for (int i = 0; i < multy_graph.edges.size(); i++) {
			final EdgeImpl<Float2, EdgeType> one = multy_graph.edges.getElementAt(i);
			for (int k = i + 1; k < multy_graph.edges.size(); k++) {
				final EdgeImpl<Float2, EdgeType> other = multy_graph.edges.getElementAt(k);
				VertexImpl<Float2, EdgeType> intersection = findIntersection(one, other);
				if (intersection != null) {
					// Log.d("intersection", toString(one), " vs",
					// toString(other));
					intersection = addIfNecessary(multy_graph, intersection);
					// Log.d(" at", intersection);

					markEdgeForReplacment(one, intersection, edges_to_add, edges_to_remove);
					markEdgeForReplacment(other, intersection, edges_to_add, edges_to_remove);
					multy_graph.edges.removeAll(edges_to_remove);
					multy_graph.edges.addAll(edges_to_add);
					edges_to_remove.clear();
					edges_to_add.clear();
					removeDuplicatingEdges(multy_graph);
					removeLoopEdges(multy_graph);
					break;
				}

			}
		}

	}

	private static <EdgeType> VertexImpl<Float2, EdgeType> findNode(final VertexImpl<Float2, EdgeType> node,
			final List<VertexImpl<Float2, EdgeType>> list) {
		for (int i = 0; i < list.size(); i++) {
			final VertexImpl<Float2, EdgeType> element = list.getElementAt(i);
			if (element.getVertexObject().isInEpsilonDistance(node.getVertexObject())) {
				return element;
			}
		}
		return null;
	}

	private static <EdgeType> VertexImpl<Float2, EdgeType> findNode(FixedFloat2 dot, Ploy2DGraph<EdgeType> graph) {
		// final VertexImpl<Point, EdgeType> n = new VertexImpl<Point,
		// EdgeType>(point);
		// return findNode(n, graph.vertices);

		for (int i = 0; i < graph.vertices.size(); i++) {
			final VertexImpl<Float2, EdgeType> element = graph.vertices.getElementAt(i);
			if (element.getVertexObject().isInEpsilonDistance(dot)) {
				return element;
			}
		}
		return null;
	}

	static private <EdgeType> void resolveOverlaps(Ploy2DGraph<EdgeType> multy_graph) {
		for (int i = 0; i < multy_graph.vertices.size(); i++) {
			final VertexImpl<Float2, EdgeType> node = multy_graph.vertices.getElementAt(i);
			resolveOverlaps(node, multy_graph);
		}
		removeLoopEdges(multy_graph);
		removeDuplicatingEdges(multy_graph);
	}

	static private <EdgeType> void resolveOverlaps(final VertexImpl<Float2, EdgeType> node, Ploy2DGraph<EdgeType> graph) {
		final List<EdgeImpl<Float2, EdgeType>> edges_to_remove = JUtils.newList();
		final List<EdgeImpl<Float2, EdgeType>> edges_to_add = JUtils.newList();
		for (int i = 0; i < graph.edges.size(); i++) {
			final EdgeImpl<Float2, EdgeType> edge = graph.edges.getElementAt(i);
			if (edge.getLeftNode() != node && edge.getRightNode() != node) {
				if (nodeLaysOn(node, edge)) {
					markEdgeForReplacment(edge, node, edges_to_add, edges_to_remove);
				}
			}
		}
		graph.edges.removeAll(edges_to_remove);
		graph.edges.addAll(edges_to_add);
	}

	static private <EdgeType> boolean nodeLaysOn(VertexImpl<Float2, EdgeType> node, EdgeImpl<Float2, EdgeType> edge) {
		double xN = 0;
		double yN = 0;
		double xA = 0;
		double yA = 0;
		double xB = 0;
		double yB = 0;

		xN = node.getVertexObject().getX();
		yN = node.getVertexObject().getY();

		xA = ((Float2) edge.getLeftNode().getVertexObject()).getX();
		yA = ((Float2) edge.getLeftNode().getVertexObject()).getY();

		xB = ((Float2) edge.getRightNode().getVertexObject()).getX();
		yB = ((Float2) edge.getRightNode().getVertexObject()).getY();

		double A = FloatMath.distance(xN, yN, xA, yA);
		double B = FloatMath.distance(xN, yN, xB, yB);
		double AB = FloatMath.distance(xB, yB, xA, yA);

		return FloatMath.EPSILON() >= FloatMath.abs(AB - A - B);
	}

	public static <EdgeType> Ploy2DGraph<EdgeType> newMultiGraph(final EditableCollection<? extends FixedFloat2> input) {

		final List<VertexImpl<Float2, EdgeType>> nodes = JUtils.newList();
		for (int i = 0; i < input.size(); i++) {
			final FixedFloat2 dot = input.getElementAt(i);
			VertexImpl<Float2, EdgeType> node = new VertexImpl<Float2, EdgeType>();
			Float2 point = Geometry.newFloat2(dot);
			node.putVertexObject(point);
			final VertexImpl<Float2, EdgeType> found = findNode(node, nodes);
			if (found != null) {
				node = found;
			}
			nodes.add(node);
		}

		final Ploy2DGraph<EdgeType> multy_graph = new Ploy2DGraph<EdgeType>();
		for (int i = 0; i < nodes.size(); i++) {
			final VertexImpl<Float2, EdgeType> point = nodes.getElementAt(i);
			if (!multy_graph.vertices.contains(point)) {
				multy_graph.vertices.add(point);
			}
		}

		for (int i = 0; i < input.size(); i++) {
			final int index = i;
			int index_next = i + 1;
			if (index_next >= input.size()) {
				index_next = 0;
			}
			FixedFloat2 point = input.getElementAt(index);
			FixedFloat2 point_next = input.getElementAt(index_next);

			VertexImpl<Float2, EdgeType> node = findNode(point, multy_graph);
			VertexImpl<Float2, EdgeType> next_node = findNode(point_next, multy_graph);
			EdgeImpl<Float2, EdgeType> edge = multy_graph.createNewEdge(node, next_node);
		}

		removeLoopEdges(multy_graph);
		removeDuplicatingEdges(multy_graph);
		resolveOverlaps(multy_graph);
		findIntersections(multy_graph);
		multy_graph.establishLinks();
		removeSingleNodes(multy_graph);

		return multy_graph;
	}

	static public <EdgeType> int indexOf(Ploy2DGraph<EdgeType> graph, VertexImpl<Float2, EdgeType> l) {
		for (int i = 0; i < graph.vertices.size(); i++) {
			VertexImpl<Float2, EdgeType> e = graph.vertices.getElementAt(i);
			if (e.getVertexObject().isInEpsilonDistance(l.getVertexObject())) {
				return i;
			}
		}
		return -1;
	}

	static public <EdgeType> String toString(Ploy2DGraph<EdgeType> graph, EdgeImpl<Float2, EdgeType> n) {
		if (n == null) {
			return null;
		}
		VertexImpl<Float2, EdgeType> l = n.getLeftNode();
		VertexImpl<Float2, EdgeType> r = n.getRightNode();
		String id = "Edge-#" + graph.indexOf(n) + "";
		int iL = indexOf(graph, l);
		int iR = indexOf(graph, r);
		if (iL >= 0) {
			if (iR >= 0) {
				return id + " [" + iL + "]-[" + iR + "]";
			} else {
				return id + " [" + iL + "]-[#" + r + "]";
			}
		} else {
			if (iR >= 0) {
				return id + " [#" + l + "]-[" + iR + "]";
			} else {
				return id + " [#" + l + "]-[#" + r + "]";
			}
		}
	}

	static private <EdgeType> VertexImpl<Float2, EdgeType> addIfNecessary(Ploy2DGraph<EdgeType> graph,
			VertexImpl<Float2, EdgeType> intersection) {
		VertexImpl<Float2, EdgeType> result = findNode(intersection, graph.vertices);
		if (result == null) {
			graph.vertices.add(intersection);
			return intersection;
		}
		return result;
	}

	static private <EdgeType> void markEdgeForReplacment(EdgeImpl<Float2, EdgeType> edge,
			VertexImpl<Float2, EdgeType> node, List<EdgeImpl<Float2, EdgeType>> edges_to_add,
			List<EdgeImpl<Float2, EdgeType>> edges_to_remove) {
		// Log.d("node ", node, " cuts ", edge);
		final EdgeImpl<Float2, EdgeType> new_edge_left = new EdgeImpl<Float2, EdgeType>();
		new_edge_left.setRightNode(node);
		new_edge_left.setLeftNode(edge.getLeftNode());

		final EdgeImpl<Float2, EdgeType> new_edge_right = new EdgeImpl<Float2, EdgeType>();
		new_edge_right.setRightNode(edge.getRightNode());
		new_edge_right.setLeftNode(node);

		edges_to_add.add(new_edge_left);
		edges_to_add.add(new_edge_right);
		edges_to_remove.add(edge);

		// Log.d("     ", new_edge_left);
		// Log.d("     ", new_edge_right);
	}

	static private <EdgeType> VertexImpl<Float2, EdgeType> findIntersection(final EdgeImpl<Float2, EdgeType> one,
			final EdgeImpl<Float2, EdgeType> other) {
		if (one.isNeighbour(other)) {
			return null;
		}

		double one_a_x = ((Float2) one.getLeftNode().getVertexObject()).getX();
		double one_A_y = ((Float2) one.getLeftNode().getVertexObject()).getY();
		double one_B_x = ((Float2) one.getRightNode().getVertexObject()).getX();
		double one_B_y = ((Float2) one.getRightNode().getVertexObject()).getY();
		double other_A_x = ((Float2) other.getLeftNode().getVertexObject()).getX();
		double other_A_y = ((Float2) other.getLeftNode().getVertexObject()).getY();
		double other_B_x = ((Float2) other.getRightNode().getVertexObject()).getX();
		double other_B_y = ((Float2) other.getRightNode().getVertexObject()).getY();

		double d = (other_B_y - other_A_y) * (one_B_x - one_a_x) - (other_B_x - other_A_x) * (one_B_y - one_A_y);
		if (FloatMath.isWithinEpsilon(d)) {
			return null;
		}

		// i.intersectLines(x1, y1, x2, y2, x3, y3, x4, y4, intersection)
		VertexImpl<Float2, EdgeType> node = new VertexImpl<Float2, EdgeType>(Geometry.newFloat2());
		double ua = ((other_B_x - other_A_x) * (one_A_y - other_A_y) - (other_B_y - other_A_y) * (one_a_x - other_A_x))
				/ d;
		node.getVertexObject().setXY(one_a_x + (one_B_x - one_a_x) * ua, one_A_y + (one_B_y - one_A_y) * ua);

		if (nodeLaysOn(node, one) && nodeLaysOn(node, other)) {
			return node;
		}
		return null;
	}

	static private <EdgeType> void removeSingleNodes(Ploy2DGraph<EdgeType> multy_graph) {
		boolean remove = false;
		do {
			remove = false;
			final List<VertexImpl<Float2, EdgeType>> nodes_to_remove = JUtils.newList();
			// final List<Node> isolated_nodes = new List<Node>();
			for (int i = 0; i < multy_graph.vertices.size(); i++) {
				VertexImpl<Float2, EdgeType> node = multy_graph.vertices.getElementAt(i);
				// if (node.getLinks().size() == 0) {
				// isolated_nodes.add(node);
				// remove = true;
				// }
				if (node.getLinks().size() <= 1) {
					nodes_to_remove.add(node);
					remove = true;
				}
			}
			// this.nodes.removeAll(isolated_nodes);
			for (int i = 0; i < nodes_to_remove.size(); i++) {
				VertexImpl<Float2, EdgeType> removal = nodes_to_remove.getElementAt(i);
				multy_graph.vertices.remove(removal);
				Set<EdgeImpl<Float2, EdgeType>> brocken_links = removal.getLinks();
				for (int k = 0; k < brocken_links.size(); k++) {
					EdgeImpl<Float2, EdgeType> bad_link = brocken_links.getElementAt(k);
					bad_link.getOtherNode(removal).getLinks().remove(bad_link);
					multy_graph.edges.remove(bad_link);
				}
			}
		} while (remove);

	}

	static private <EdgeType> boolean noNewExitsFromHere(EdgeImpl<Float2, EdgeType> direction,
			List<EdgeImpl<Float2, EdgeType>> testedExits) {
		for (int i = 0; i < testedExits.size(); i++) {
			EdgeImpl<Float2, EdgeType> test = testedExits.getElementAt(i);
			if (test.getRightNode() == direction.getRightNode() && test.getLeftNode() == direction.getLeftNode()) {
				return true;
			}
		}

		return false;
	}

	static private <EdgeType> EdgeImpl<Float2, EdgeType> findNextBorderEdge(EdgeImpl<Float2, EdgeType> current) {
		EdgeImpl<Float2, EdgeType> next = findNextBorderEdge(current.getRightNode(), current);
		return next;
	}

	static private <EdgeType> CustomAngle edgeDirection(final VertexImpl<Float2, EdgeType> start,
			final VertexImpl<Float2, EdgeType> end) {
		final VectorTool tool = MathTools.newVectorTool();
		tool.X = end.getVertexObject().getX() - start.getVertexObject().getX();
		tool.Y = end.getVertexObject().getY() - start.getVertexObject().getY();
		tool.XYtoAR();
		tool.A = tool.A;
		return Angles.newAngle(tool.A);
	}

	static private <EdgeType> EdgeImpl<Float2, EdgeType> findNextBorderEdge(VertexImpl<Float2, EdgeType> start,
			EdgeImpl<Float2, EdgeType> ignore) {
		final Set<EdgeImpl<Float2, EdgeType>> links = start.getLinks();
		if (links.size() == 0) {
			throw new Error("Isolated node: " + start);
		}

		EdgeImpl<Float2, EdgeType> candidate = null;
		final CustomAngle candidate_angle = Angles.ZERO();
		final CustomAngle other_candidate_angle = Angles.ZERO();
		for (int i = 0; i < links.size(); i++) {
			final EdgeImpl<Float2, EdgeType> other_candidate = links.getElementAt(i);
			if (other_candidate == ignore) {
				// Log.d("    skip", toString(other_candidate));
				continue;
				// other_candidate_angle.setValue(Angle.TWICE_PI);
			}
			boolean switch_direction = false;
			// Log.d("cheking candidate", toString(other_candidate));
			VertexImpl<Float2, EdgeType> other_end = other_candidate.getOtherNode(start);
			if (other_candidate.getLeftNode() == other_end) {
				switch_direction = true;
				other_candidate.switchDirection();
			}
			other_candidate_angle.setValue(edgeToEdgeAngle(ignore, other_candidate));
			if (switch_direction) {
				other_candidate.switchDirection();
			}

			if (candidate == null) {
				candidate = other_candidate;
				// Log.d("    switching candidate to", toString(candidate));
				candidate_angle.setValue(other_candidate_angle);
				continue;
			} else {
				final boolean new_candidate_is_better = candidate_angle.getMagnitude() > other_candidate_angle
						.getMagnitude();

				// Log.d("    candidate_angle", candidate_angle);
				// Log.d("    other_candidate_angle", other_candidate_angle);

				if (new_candidate_is_better) {
					// Log.d("    switching candidate from",
					// toString(candidate));
					candidate = other_candidate;
					// Log.d("                          to",
					// toString(candidate));
					candidate_angle.setValue(other_candidate_angle);
				} else {
					// Log.d("    reject candidate", toString(other_candidate));
				}
			}
		}
		VertexImpl<Float2, EdgeType> other_end = candidate.getOtherNode(start);
		if (candidate.getLeftNode() == other_end) {
			candidate.switchDirection();
		}
		return candidate;
	}

	static private <EdgeType> CustomAngle edgeToEdgeAngle(EdgeImpl<Float2, EdgeType> from, EdgeImpl<Float2, EdgeType> to) {
		CustomAngle direction_from = null;
		if (from == null) {
			direction_from = Angles.newAngle();
		} else {
			direction_from = edgeDirection(from.getRightNode(), from.getLeftNode());
		}
		CustomAngle direction_to = edgeDirection(to.getLeftNode(), to.getRightNode());
		direction_to.makePositive();
		direction_from.makePositive();

		CustomAngle delta = Angles.subtract(direction_to, direction_from, Angles.newAngle());
		delta.setValue(delta.toRadians() + FloatMath.VAL_2PI());

		return delta;
	}

	static private <EdgeType> VertexImpl<Float2, EdgeType> findTheMostDistantNode(Ploy2DGraph<EdgeType> multy_graph) {
		VertexImpl<Float2, EdgeType> result = multy_graph.vertices.getElementAt(0);
		for (int i = 0; i < multy_graph.vertices.size(); i++) {
			VertexImpl<Float2, EdgeType> element = multy_graph.vertices.getElementAt(i);
			if (element.getVertexObject().getX() >= result.getVertexObject().getX()) {
				result = element;
			}
		}
		return result;
	}

	static private <EdgeType> void moveTaleFromTo(List<VertexImpl<Float2, EdgeType>> from,
			List<VertexImpl<Float2, EdgeType>> to, int index) {
		int n = from.size();
		for (int i = index; i < n; i++) {
			VertexImpl<Float2, EdgeType> remov = from.removeElementAt(index);
			to.add(remov);
		}
	}

	static private <EdgeType> int indexOf(List<VertexImpl<Float2, EdgeType>> unpainted_nodes,
			VertexImpl<Float2, EdgeType> node) {
		for (int i = 0; i < unpainted_nodes.size(); i++) {
			VertexImpl<Float2, EdgeType> e = unpainted_nodes.getElementAt(i);
			if (e == node) {
				return i;
			}
		}
		return -1;
	}

	static private <EdgeType> void visit(VertexImpl<Float2, EdgeType> node,
			List<VertexImpl<Float2, EdgeType>> visited_nodes) {
		visited_nodes.add(node);
	}

	public static <EdgeType> List<PathInGraph<Float2, EdgeType>> extractSimpleCycles(Ploy2DGraph<EdgeType> multy_graph) {
		List<VertexImpl<Float2, EdgeType>> order = findBorderNodes(multy_graph);

		List<VertexImpl<Float2, EdgeType>> visited_nodes = JUtils.newList();
		List<VertexImpl<Float2, EdgeType>> not_painted_nodes = JUtils.newList();
		not_painted_nodes.addAll(order);
		List<List<VertexImpl<Float2, EdgeType>>> colors = JUtils.newList();
		List<VertexImpl<Float2, EdgeType>> color;
		List<VertexImpl<Float2, EdgeType>> queue = JUtils.newList();
		int current_index = 0;
		VertexImpl<Float2, EdgeType> current = null;
		boolean processing = not_painted_nodes.size() > 0 && current_index < order.size();
		while (processing) {
			current = order.getElementAt(current_index);
			current_index++;
			if (indexOf(visited_nodes, current) == -1) {
				queue.add(current);
				visited_nodes.add(current);
				// Log.d("visit:", toString(current));
			} else {
				// Log.d("  loop", toString(current));
				int index = indexOf(queue, current);
				color = JUtils.newList();
				moveTaleFromTo(queue, color, index + 1);
				not_painted_nodes.removeAll(color);
				not_painted_nodes.remove(current);
				color.add(current);
				if (color.size() >= 3) {
					colors.add(color);
				}
				// printNodes("color#", color, this);
				// printNodes("queue#", queue, this);
				// printNodes("not_painted_nodes#", not_painted_nodes, this);
			}
			processing = not_painted_nodes.size() > 0 && current_index < order.size();
		}

		List<PathInGraph<Float2, EdgeType>> result = JUtils.newList();
		for (int i = 0; i < colors.size(); i++) {
			color = colors.getElementAt(i);
			final int n = color.size();
			final PathInGraph<Float2, EdgeType> cycle = toCycleGraph(multy_graph, color);
			result.add(cycle);
		}

		return result;

	}

	static <EdgeType> List<VertexImpl<Float2, EdgeType>> findBorderNodes(Ploy2DGraph<EdgeType> multy_graph) {
		if (multy_graph.vertices.size() < 3) {
			return JUtils.newList();
		}
		// if (this.nodes.size() < 3) {
		// this.nodes.clear();
		// this.edges.clear();
		// }

		final VertexImpl<Float2, EdgeType> start_node = findTheMostDistantNode(multy_graph);
		// Log.d("start_node", toString(start_node));
		List<VertexImpl<Float2, EdgeType>> visiting_order = JUtils.newList();
		visiting_order.add(start_node);

		final EdgeImpl<Float2, EdgeType> start_direction = findNextBorderEdge(start_node, null);
		// Log.d("start_direction", toString(start_direction));

		List<EdgeImpl<Float2, EdgeType>> testedExits = JUtils.newList();
		List<EdgeImpl<Float2, EdgeType>> transitions = JUtils.newList();
		transitions.add(start_direction);
		testedExits.add(new EdgeImpl<Float2, EdgeType>(start_direction));

		EdgeImpl<Float2, EdgeType> current_direction = start_direction;
		VertexImpl<Float2, EdgeType> currentNode = start_direction.getRightNode();

		visiting_order.add(currentNode);

		boolean cycle_closed = false;
		while (!cycle_closed) {
			// Log.d(" leaving", toString(currentNode));
			current_direction = findNextBorderEdge(current_direction);
			currentNode = current_direction.getRightNode();
			// Log.d("   taking course", toString(current_direction));
			// Log.d("     destination", toString(currentNode));

			if (noNewExitsFromHere(current_direction, testedExits)) {

				cycle_closed = true;

			} else {
				// not_visited_nodes.remove(currentNode);

			}
			visiting_order.add(currentNode);
			transitions.add(current_direction);
			testedExits.add(new EdgeImpl<Float2, EdgeType>(current_direction));
			// printNodes("visiting_order", visiting_order, this);
			// printEdges("transitions", transitions, this);

		}
		// printNodes("visiting_order", visiting_order, this);
		// printEdges("transitions", transitions, this);

		return visiting_order;
	}

	static public <EdgeType> void removeDuplicatingEdges(Ploy2DGraph<EdgeType> multy_graph) {
		final List<EdgeImpl<Float2, EdgeType>> edges_to_remove = JUtils.newList();
		for (int i = 0; i < multy_graph.edges.size(); i++) {
			final EdgeImpl<Float2, EdgeType> one = multy_graph.edges.getElementAt(i);
			for (int k = i + 1; k < multy_graph.edges.size(); k++) {
				final EdgeImpl<Float2, EdgeType> other = multy_graph.edges.getElementAt(k);
				if (isDuplicatingEdge(one, other, multy_graph)) {
					// Log.d("reduction", toString(one), "is duplicated by ",
					// toString(other));

					edges_to_remove.add(other);
				}
			}
		}
		multy_graph.edges.removeAll(edges_to_remove);
	}

	static private <EdgeType> CyclePathImpl<Float2, EdgeType> toCycleGraph(Ploy2DGraph<EdgeType> multy_graph,
			List<VertexImpl<Float2, EdgeType>> color) {
		int n = color.size();
		CyclePathImpl<Float2, EdgeType> cycle = new CyclePathImpl<Float2, EdgeType>();
		cycle.setNumberOfVetices(n);
		for (int i = 0; i < n; i++) {
			//
			cycle//
			.getState(i)//
					.getVertex().putVertexObject(Geometry.newFloat2());

			Float2 point = cycle//
					.getState(i)//
					.getVertex()//
					.getVertexObject()//
			;

			point.set(//
			color//
			.getElementAt(i)//
					.getVertexObject());
		}
		return cycle;
	}
}
