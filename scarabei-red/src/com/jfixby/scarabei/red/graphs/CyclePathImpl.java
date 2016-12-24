
package com.jfixby.scarabei.red.graphs;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.graphs.PathInGraph;
import com.jfixby.scarabei.api.log.L;

public class CyclePathImpl<VertexType, EdgeType> implements PathInGraph<VertexType, EdgeType> {

	@Override
	public String toString () {
		return "GraphCycle" + this.states + "";
	}

	private final List<StateImpl<VertexType, EdgeType>> states = Collections.newList();
	private final List<StepImpl<VertexType, EdgeType>> steps = Collections.newList();

	@Override
	public int numberOfSteps () {
		return this.states.size();
	}

	@Override
	public int numberOfStates () {
		return this.states.size();
	}

	@Override
	public StateImpl<VertexType, EdgeType> getState (final int state_number) {
		return this.states.getElementAt(state_number);
	}

	@Override
	public StepImpl<VertexType, EdgeType> getStep (final int step_number) {
		return this.steps.getElementAt(step_number);
	}

	public void setNumberOfVetices (final int n) {
		if (n < 0) {
			Err.reportError("Negative size graph: " + n);
		}
		if (n == 0) {
			this.states.clear();
			return;
		}
		int current_size = this.states.size();
		if (current_size == n) {
			return;
		}
		// n>0, n!=current_size;
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

	private int removeVertex (final int current_size) {// current_size>1;
		final StateImpl<VertexType, EdgeType> removed = this.states.removeElementAt(current_size - 1);

		final StateImpl<VertexType, EdgeType> first = this.states.getElementAt(0);
		final StateImpl<VertexType, EdgeType> last = this.states.getElementAt(current_size - 2);

		final StepImpl<VertexType, EdgeType> edge = first.getLeftEdge();
		edge.setLeftState(last);
		last.setRightEdge(edge);

		final StepImpl<VertexType, EdgeType> removed_left_edge = removed.getLeftEdge();
		removed.setLeftEdge(null);
		removed.setRightEdge(null);
		removed_left_edge.setLeftState(null);
		removed_left_edge.setRightState(null);

		return this.states.size();
	}

	private int addVertex (final int current_size) {
		if (current_size == 0) {
			final StateImpl<VertexType, EdgeType> new_vertex = this.newVertex();
			final StepImpl<VertexType, EdgeType> new_edge = new StepImpl<VertexType, EdgeType>();
			new_edge.setLeftState(new_vertex);
			new_edge.setRightState(new_vertex);
			new_vertex.setRightEdge(new_edge);
			new_vertex.setLeftEdge(new_edge);
			this.states.add(new_vertex);
			return 1;
		}

		final StateImpl<VertexType, EdgeType> first = this.states.getElementAt(0);
		final StateImpl<VertexType, EdgeType> last = this.states.getElementAt(current_size - 1);

		final StateImpl<VertexType, EdgeType> new_vertex = this.newVertex();
		final StepImpl<VertexType, EdgeType> new_edge = new StepImpl<VertexType, EdgeType>();

		last.setRightEdge(new_edge);
		new_edge.setLeftState(last);

		new_edge.setRightState(new_vertex);
		new_vertex.setLeftEdge(new_edge);

		final StepImpl<VertexType, EdgeType> first_left_edge = first.getLeftEdge();
		new_vertex.setRightEdge(first_left_edge);
		first_left_edge.setLeftState(new_vertex);

		this.states.add(new_vertex);

		return this.states.size();

	}

	private StateImpl<VertexType, EdgeType> newVertex () {
		return new StateImpl<VertexType, EdgeType>().setVertex(new VertexImpl<VertexType, EdgeType>());
	}

	public void print () {
		L.d("---GraphCycle---");
		if (this.states.size() == 0) {
			return;
		}

		for (int i = 0; i < this.states.size(); i++) {
			final StateImpl<VertexType, EdgeType> ver = this.states.getElementAt(i);
			L.d("  [" + i + "]", ver.toString());
		}

	}

	@Override
	public List<VertexType> toVerticesList () {
		final List<VertexType> vertices = Collections.newList();
		for (int i = 0; i < this.numberOfSteps(); i++) {
			final VertexType object = this.getState(i).getVertex().getVertexObject();
			vertices.add(object);
		}

		return vertices;
	}

}
