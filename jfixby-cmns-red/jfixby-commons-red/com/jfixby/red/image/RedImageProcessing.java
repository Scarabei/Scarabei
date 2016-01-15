package com.jfixby.red.image;

import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.ImageProcessingComponent;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;

public abstract class RedImageProcessing implements ImageProcessingComponent {

	@Override
	public ArrayColorMapSpecs newColorMapSpecs() {
		return new RedColorMapSpecs();
	}

	@Override
	public LambdaColorMapSpecs newLambdaColorMapSpecs() {
		return new RedLambdaColorMapSpecs();
	}



}
