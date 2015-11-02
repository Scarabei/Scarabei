package com.jfixby.cmns.api.image;

public interface ImageProcessingComponent {

	ColorMapSpecs newColorMapSpecs();

	ArrayColorMap newArrayColorMap(ColorMapSpecs color_function_specs);

}
