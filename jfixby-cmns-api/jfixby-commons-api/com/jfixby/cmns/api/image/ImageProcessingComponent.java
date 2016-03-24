package com.jfixby.cmns.api.image;

public interface ImageProcessingComponent {

    ArrayColorMapSpecs newArrayColorMapSpecs();

    ArrayColorMap newArrayColorMap(ArrayColorMapSpecs color_function_specs);

    ColorMapSpecs newColorMapSpecs();

    ColorMap newColorMap(ColorMapSpecs lambda_specs);

    ColoredλImageCache newImageCache(int width, int height);

    ColoredλImage scale(ColoredλImage base, float scaleX, float scaleY);

    GrayλImage scale(GrayλImage base, float scaleX, float scaleY);

    GrayλImage minus(GrayλImage base, GrayλImage diff);

    GrayλImage multiply(GrayλImage image, float mult);

    GrayMap newGrayMap(GrayλImage lambda, int width, int height);

    GrayMapSpecs newGrayMapSpecs();

    GrayλImage roundArguments(GrayλImage image);

    ArrayGrayMapSpecs newArrayGrayMapSpecs();

    ArrayGrayMap newArrayGrayMap(ArrayGrayMapSpecs specs);

    GrayλImage plus(GrayλImage base, GrayλImage add);

    int roundArgument(float x);

    GrayλImage ONE();

    GrayλImage ZERO();

    ColorMap newColorMap(ColoredλImage lambda, int width, int height);

}
