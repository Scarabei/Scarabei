
package com.jfixby.red.image;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.image.GrayλImage;

public class RedGrayMap implements GrayMap {

	private int width;
	private int height;
	private GrayλImage lambda;

	public RedGrayMap (GrayλImage lambda, int width, int height) {
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
	public GrayλImage getLambdaImage () {
		return lambda;
	}

}
