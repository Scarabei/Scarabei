
package com.jfixby.scarabei.red.image;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.image.GrayMap;
import com.jfixby.scarabei.api.image.GrayLambdaImage;

public class RedGrayMap implements GrayMap {

	private int width;
	private int height;
	private GrayLambdaImage lambda;

	public RedGrayMap (GrayLambdaImage lambda, int width, int height) {
		this.width = width;
		this.height = height;
		this.lambda = Debug.checkNull("lambda image", lambda);
	}

	@Override
	public float valueAt (float x, float y) {
		return lambda.valueAt(x, y);
	}

	@Override
	public int getWidth () {
		return width;
	}

	@Override
	public int getHeight () {
		return height;
	}

	@Override
	public GrayLambdaImage getLambdaImage () {
		return lambda;
	}

}
