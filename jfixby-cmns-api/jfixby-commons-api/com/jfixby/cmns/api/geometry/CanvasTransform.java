package com.jfixby.cmns.api.geometry;

import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.math.Angle;

public interface CanvasTransform extends Transform {

	public void setOffset(double x, double y);

	public void setOffsetX(double x);

	public void setOffsetY(double y);

	public void setOffset(FixedFloat2 offset);

	public FixedFloat2 getOffset();

	public double getOffsetX();

	public double getOffsetY();

	public void setRotation(Angle rotation);

	public void setRotation(double rotation);

	public Angle getRotation();

	public void setSkew(double skewx, double skewy);

	public void setSkewX(double skew);

	public void setSkewY(double skew);

	public void setSkew(FixedFloat2 skew);

	public FixedFloat2 getSkew();

	public double getSkewX();

	public double getSkewY();

	public void setScale(double scale_x, double scale_y);

	public void setScaleX(double scale);

	public void setScaleY(double scale);

	public void setScale(FixedFloat2 scale);

	public FixedFloat2 getScale();

	public double getScaleX();

	public double getScaleY();

	

//	public CanvasPosition getPosition();

}
