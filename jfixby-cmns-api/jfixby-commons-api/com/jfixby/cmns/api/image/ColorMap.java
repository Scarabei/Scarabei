package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;

public interface ColorMap {

	int getWidth();

	int getHeight();

	Color getValue(int x, int y);

	LambdaImage getRedChannel();

	LambdaImage getGreenChannel();

	LambdaImage getBlueChannel();

	LambdaImage getAlphaChannel();

	LambdaImage getGrayscale(float grayscale_alpha, float grayscale_betta, float grayscale_gamma);

	LambdaImage getGrayscale(); // 0.21 R + 0.72 G + 0.07 B.
}
