
package com.jfixby.scarabei.api.geometry.projections;

import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.math.Angle;

public interface CanvasTransform extends Transform2D {

	public void setOffset (double x, double y);

	public void setOffsetX (double x);

	public void setOffsetY (double y);

	public void setOffset (ReadOnlyFloat2 offset);

	public ReadOnlyFloat2 getOffset ();

	public double getOffsetX ();

	public double getOffsetY ();

	public void setRotation (Angle rotation);

	public void setRotation (double rotation);

	public Angle getRotation ();

	public void setSkew (double skewx, double skewy);

	public void setSkewX (double skew);

	public void setSkewY (double skew);

	public void setSkew (ReadOnlyFloat2 skew);

	public ReadOnlyFloat2 getSkew ();

	public double getSkewX ();

	public double getSkewY ();

	public void setScale (double scale_x, double scale_y);

	public void setScaleX (double scale);

	public void setScaleY (double scale);

	public void setScale (ReadOnlyFloat2 scale);

	public ReadOnlyFloat2 getScale ();

	public double getScaleX ();

	public double getScaleY ();

// public CanvasPosition getPosition();

}
