package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.lambda.λImage;

public interface ColorMap {

	int getWidth();

	int getHeight();

	Color getValue(int x, int y);

	λImage getLambdaImage();
}
