
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;

public interface Triangle {

	public Vertex A ();

	public Vertex B ();

	public Vertex C ();

	public boolean containsPoint (double worldx, double worldy);

	public boolean containsPoint (ReadOnlyFloat2 point);

}
