package com.jfixby.cmns.api.image;

public interface ImageProcessingComponent {

	ArrayColorMapSpecs newColorMapSpecs();

	ArrayColorMap newArrayColorMap(ArrayColorMapSpecs color_function_specs);

	LambdaColorMapSpecs newLambdaColorMapSpecs();

	LambdaColorMap newLambdaColorMap(LambdaColorMapSpecs lambda_specs);

}
