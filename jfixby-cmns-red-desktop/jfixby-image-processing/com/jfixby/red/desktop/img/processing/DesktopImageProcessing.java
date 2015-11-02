package com.jfixby.red.desktop.img.processing;

import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ColorMap;
import com.jfixby.cmns.api.image.ColorMapSpecs;
import com.jfixby.cmns.api.image.ImageProcessingComponent;

public class DesktopImageProcessing implements ImageProcessingComponent {

	@Override
	public ColorMapSpecs newColorMapSpecs() {
		return new DesktopColorFunctionSpecs();
	}

	@Override
	public ArrayColorMap newArrayColorMap(ColorMapSpecs color_function_specs) {
		return new DesktopColorFunction(color_function_specs);
	}

}
