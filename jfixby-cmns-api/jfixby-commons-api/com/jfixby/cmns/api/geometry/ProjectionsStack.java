
package com.jfixby.cmns.api.geometry;

public interface ProjectionsStack extends Projection {

	void push (Projection projection);

	Projection pop ();

	Projection peek ();

	int size ();

}
