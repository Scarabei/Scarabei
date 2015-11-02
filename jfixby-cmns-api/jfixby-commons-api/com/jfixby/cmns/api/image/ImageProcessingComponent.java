package com.jfixby.cmns.api.image;

public interface ImageProcessingComponent {

	ColorFunctionSpecs newColorFunctionSpecs();

	ColorFunction newColorFunction(ColorFunctionSpecs color_function_specs);

}
