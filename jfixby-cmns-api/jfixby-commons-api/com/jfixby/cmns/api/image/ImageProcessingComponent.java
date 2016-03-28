package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.ColorProjector;
import com.jfixby.cmns.api.color.GraySet;

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

    IndexedColorMapSpecs newIndexedColorMapSpecs();

    GrayIndexedλImage index(GrayλImage lambdaImage, GraySet palette);

    ColoredλImage index(ColoredλImage lambdaImage, ColorProjector palette);

    ColorMap newColorMap(int width, int height, GrayλImage alpha, GrayλImage red, GrayλImage green, GrayλImage blue);

    ColoredλImage merge(GrayλImage alpha, GrayλImage red, GrayλImage green, GrayλImage blue);

    ColorMap scaleTo(ColorMap lambdaImage, int width, int height);

    void scanImage(ColorMap image, PixelByPixelAction action);

    ColorMap removeAlpha(ColorMap original_image);

}
