package com.jfixby.cmns.api.graphs;

import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.floatn.FixedFloat2;

public class Graphs {

	static private ComponentInstaller<GraphsComponent> componentInstaller = new ComponentInstaller<GraphsComponent>(
			"Graphs");

	public static final void installComponent(GraphsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final GraphsComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final GraphsComponent component() {
		return componentInstaller.getComponent();
	}

	public static <VertexType, EdgeType> MultiGraph<VertexType, EdgeType> newUndirectedGraph() {
		return invoke().newUndirectedGraph();
	}

	public static <EdgeType> PolyGraph<EdgeType> newPolyGraph(EditableCollection<? extends FixedFloat2> vertices) {
		return invoke().newPolyGraph(vertices);
	}

}
