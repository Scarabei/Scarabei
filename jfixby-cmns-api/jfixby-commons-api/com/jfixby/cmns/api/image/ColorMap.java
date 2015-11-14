package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.math.FixedInt2;

public interface ColorMap {

	int getWidth();

	int getHeight();

	Color getValue(int x, int y);

	λFunction<FixedInt2, Color> getLambdaImage();
}
