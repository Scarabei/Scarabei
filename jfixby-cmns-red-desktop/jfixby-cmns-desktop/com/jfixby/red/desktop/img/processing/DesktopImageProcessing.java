package com.jfixby.red.desktop.img.processing;

import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.ImageProcessingComponent;
import com.jfixby.cmns.api.image.LambdaColorMap;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.red.image.RedColorMapSpecs;
import com.jfixby.red.image.RedImageProcessing;

public class DesktopImageProcessing extends RedImageProcessing implements ImageProcessingComponent {

	@Override
	public ArrayColorMapSpecs newColorMapSpecs() {
		return new RedColorMapSpecs();
	}

	@Override
	public ArrayColorMap newArrayColorMap(ArrayColorMapSpecs color_function_specs) {
		return new DesktopColorFunction(color_function_specs);
	}

	@Override
	public LambdaColorMap newLambdaColorMap(LambdaColorMapSpecs lambda_specs) {
		return new DesktopLambdaColorMap(lambda_specs);
	}

}
