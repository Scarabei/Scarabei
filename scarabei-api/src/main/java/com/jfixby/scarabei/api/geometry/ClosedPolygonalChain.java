
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.floatn.Float2;

public interface ClosedPolygonalChain extends GeometryFigure {

	int size ();

	Vertex getVertex (int index);

	public void clear ();

	// public void setup(Collection<? extends Dot> vertices);

	// public void addVertices(Collection<? extends Dot> vertices);

	// public void addVertex(Dot vertex);

	public void setSize (int n);

	public PolyTriangulation getTriangulation ();

	public Collection<Vertex> listVertices ();

	void addVertex (ReadOnlyFloat2 point);

	void print (String tag);

	boolean containsPoint (double point_x, double point_y);

	void setupVertices (Collection<Float2> input);

}
