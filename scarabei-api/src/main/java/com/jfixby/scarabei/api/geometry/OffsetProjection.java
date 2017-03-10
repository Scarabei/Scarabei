
package com.jfixby.scarabei.api.geometry;

public interface OffsetProjection extends Projection {

	void setOffsetX (double x);

	void setOffsetY (double y);

	public double getOffsetX ();

	public double getOffsetY ();

}
