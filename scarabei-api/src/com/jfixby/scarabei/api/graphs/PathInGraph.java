
package com.jfixby.scarabei.api.graphs;

import com.jfixby.scarabei.api.collections.List;

public interface PathInGraph<VertexType, EdgeType> {

	public int numberOfStates ();

	public PathState<VertexType, EdgeType> getState (int state_number);

	public int numberOfSteps ();

	public PathStep<VertexType, EdgeType> getStep (int step_number);

	public List<VertexType> toVerticesList ();

}
