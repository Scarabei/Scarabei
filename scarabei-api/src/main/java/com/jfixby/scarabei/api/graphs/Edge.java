
package com.jfixby.scarabei.api.graphs;

public interface Edge<EdgeType> {

	void putObject (EdgeType object);

	EdgeType getObject ();

}
