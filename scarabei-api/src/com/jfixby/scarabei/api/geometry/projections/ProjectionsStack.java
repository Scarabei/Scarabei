
package com.jfixby.scarabei.api.geometry.projections;

public interface ProjectionsStack extends Projection {

	void push (Projection projection);

	Projection pop ();

	Projection peek ();

	int size ();

}
