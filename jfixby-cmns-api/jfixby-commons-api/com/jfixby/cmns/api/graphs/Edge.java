package com.jfixby.cmns.api.graphs;

public interface Edge<EdgeType> {

	void putObject(EdgeType object);

	EdgeType getObject();

}
