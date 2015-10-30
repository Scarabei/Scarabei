package com.jfixby.cmns.api.geometry;

import com.jfixby.cmns.api.floatn.FixedFloat2;

public interface Triangle {

	public Vertex A();

	public Vertex B();

	public Vertex C();

	public boolean containsPoint(double worldx, double worldy);

	public boolean containsPoint(FixedFloat2 point);

}
