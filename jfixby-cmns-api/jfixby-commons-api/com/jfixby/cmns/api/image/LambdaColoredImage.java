package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.floatn.FixedFloat2;

public interface LambdaColoredImage {

	public Color value(FixedFloat2 point);

}
