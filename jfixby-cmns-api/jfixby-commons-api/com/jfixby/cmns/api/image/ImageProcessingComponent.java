package com.jfixby.cmns.api.image;

public interface ImageProcessingComponent {

	ColorMapSpecs newColorMapSpecs();

	ColorMap newArrayColorMap(ColorMapSpecs color_function_specs);

}
