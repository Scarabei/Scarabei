package com.jfixby.cmns.api.image;

public interface ImageProcessingComponent {

    ArrayColorMapSpecs newArrayColorMapSpecs();

    ArrayColorMap newArrayColorMap(ArrayColorMapSpecs color_function_specs);

    ColorMapSpecs newColorMapSpecs();

    ColorMap newColorMap(ColorMapSpecs lambda_specs);

    ColoredλImageCache newImageCache(int width, int height);

}
