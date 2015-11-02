package com.jfixby.red.image;

import com.jfixby.cmns.api.geometry.Rectangle;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.cmns.api.image.LambdaImage;

public class RedLambdaColorMapSpecs implements LambdaColorMapSpecs {

	private LambdaImage aplha;
	private LambdaImage red;
	private LambdaImage green;
	private LambdaImage blue;
	private Rectangle rectangle;

	@Override
	public void setAlphaChannel(LambdaImage aplha) {
		this.aplha = aplha;
	}

	@Override
	public void setRedChannel(LambdaImage red) {
		this.red = red;
	}

	@Override
	public void setGreenChannel(LambdaImage green) {
		this.green = green;
	}

	@Override
	public void setBlueChannel(LambdaImage blue) {
		this.blue = blue;
	}

	@Override
	public void setArea(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public Rectangle getArea() {
		return rectangle;
	}

	@Override
	public LambdaImage getAlphaChannel() {
		return aplha;
	}

	@Override
	public LambdaImage getRedChannel() {
		return red;
	}

	@Override
	public LambdaImage getGreenChannel() {
		return green;
	}

	@Override
	public LambdaImage getBlueChannel() {
		return blue;
	}

}
