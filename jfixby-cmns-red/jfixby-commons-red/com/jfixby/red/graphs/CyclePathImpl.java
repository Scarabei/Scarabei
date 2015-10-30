package com.jfixby.red.graphs;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.graphs.PathInGraph;
import com.jfixby.cmns.api.log.L;

public class CyclePathImpl<VertexType, EdgeType> implements PathInGraph<VertexType, EdgeType> {

	@Override
	public String toString() {
		return "GraphCycle" + states + "";
	}

	private final List<StateImpl<VertexType, EdgeType>> states = JUtils.newList();
	private final List<StepImpl<VertexType, EdgeType>> steps = JUtils.newList();

	public int numberOfSteps() {
		return this.states.size();
	}

	@Override
	public int numberOfStates() {
		return this.states.size();
	}

	@Override
	public StateImpl<VertexType, EdgeType> getState(int state_number) {
		return this.states.getElementAt(state_number);
	}

	@Override
	public StepImpl<VertexType, EdgeType> getStep(int step_number) {
		return steps.getElementAt(step_number);
	}

	public void setNumberOfVetices(final int n) {
		if (n < 0) {
			throw new Error("Negative size graph: " + n);
		}
		if (n == 0) {
			states.clear();
			return;
		}
		int current_size = states.size();
		if (current_size == n) {
			return;
		}
		// n>0, n!=current_size;
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

	private int removeVertex(final int current_size) {// current_size>1;
		StateImpl<VertexType, EdgeType> removed = this.states.removeElementAt(current_size - 1);

		StateImpl<VertexType, EdgeType> first = this.states.getElementAt(0);
		StateImpl<VertexType, EdgeType> last = this.states.getElementAt(current_size - 2);

		StepImpl<VertexType, EdgeType> edge = first.getLeftEdge();
		edge.setLeftState(last);
		last.setRightEdge(edge);

		StepImpl<VertexType, EdgeType> removed_left_edge = removed.getLeftEdge();
		removed.setLeftEdge(null);
		removed.setRightEdge(null);
		removed_left_edge.setLeftState(null);
		removed_left_edge.setRightState(null);

		return this.states.size();
	}

	private int addVertex(final int current_size) {
		if (current_size == 0) {
			final StateImpl<VertexType, EdgeType> new_vertex = newVertex();
			final StepImpl<VertexType, EdgeType> new_edge = new StepImpl<VertexType, EdgeType>();
			new_edge.setLeftState(new_vertex);
			new_edge.setRightState(new_vertex);
			new_vertex.setRightEdge(new_edge);
			new_vertex.setLeftEdge(new_edge);
			states.add(new_vertex);
			return 1;
		}

		StateImpl<VertexType, EdgeType> first = this.states.getElementAt(0);
		StateImpl<VertexType, EdgeType> last = this.states.getElementAt(current_size - 1);

		StateImpl<VertexType, EdgeType> new_vertex = newVertex();
		final StepImpl<VertexType, EdgeType> new_edge = new StepImpl<VertexType, EdgeType>();

		last.setRightEdge(new_edge);
		new_edge.setLeftState(last);

		new_edge.setRightState(new_vertex);
		new_vertex.setLeftEdge(new_edge);

		StepImpl<VertexType, EdgeType> first_left_edge = first.getLeftEdge();
		new_vertex.setRightEdge(first_left_edge);
		first_left_edge.setLeftState(new_vertex);

		states.add(new_vertex);

		return this.states.size();

	}

	private StateImpl<VertexType, EdgeType> newVertex() {
		return new StateImpl<VertexType, EdgeType>().setVertex(new VertexImpl<VertexType, EdgeType>());
	}

	public void print() {
		L.d("---GraphCycle---");
		if (this.states.size() == 0) {
			return;
		}

		for (int i = 0; i < this.states.size(); i++) {
			StateImpl<VertexType, EdgeType> ver = this.states.getElementAt(i);
			L.d("  [" + i + "]", ver.toString());
		}

	}

	@Override
	public List<VertexType> toVerticesList() {
		List<VertexType> vertices = JUtils.newList();
		for (int i = 0; i < this.numberOfSteps(); i++) {
			VertexType object = this.getState(i).getVertex().getVertexObject();
			vertices.add(object);
		}

		return vertices;
	}

}
