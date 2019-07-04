
package com.jfixby.scarabei.api.image;

import com.jfixby.scarabei.api.color.ColorProjector;
import com.jfixby.scarabei.api.color.GraySet;

public interface ImageProcessingComponent {

	ArrayColorMapSpecs newArrayColorMapSpecs ();

	ArrayColorMap newArrayColorMap (ArrayColorMapSpecs color_function_specs);

	ColorMapSpecs newColorMapSpecs ();

	ColorMap newColorMap (ColorMapSpecs lambda_specs);

	ColoredLambdaImageCache newImageCache (int width, int height);

	ColoredLambdaImage scale (ColoredLambdaImage base, float scaleX, float scaleY);

	GrayLambdaImage scale (GrayLambdaImage base, float scaleX, float scaleY);

	GrayLambdaImage minus (GrayLambdaImage base, GrayLambdaImage diff);

	GrayLambdaImage multiply (GrayLambdaImage image, float mult);

	GrayMap newGrayMap (GrayLambdaImage lambda, int width, int height);

	GrayMapSpecs newGrayMapSpecs ();

	GrayLambdaImage roundArguments (GrayLambdaImage image);

	ArrayGrayMapSpecs newArrayGrayMapSpecs ();

	ArrayGrayMap newArrayGrayMap (ArrayGrayMapSpecs specs);

	GrayLambdaImage plus (GrayLambdaImage base, GrayLambdaImage add);

	int roundArgument (float x);

	GrayLambdaImage ONE ();

	GrayLambdaImage ZERO ();

	ColorMap newColorMap (ColoredLambdaImage lambda, int width, int height);

	IndexedColorMapSpecs newIndexedColorMapSpecs ();

	GrayIndexedLambdaImage index (GrayLambdaImage lambdaImage, GraySet palette);

	ColoredLambdaImage index (ColoredLambdaImage lambdaImage, ColorProjector palette);

	ColorMap newColorMap (int width, int height, GrayLambdaImage alpha, GrayLambdaImage red, GrayLambdaImage green, GrayLambdaImage blue);

	ColoredLambdaImage merge (GrayLambdaImage alpha, GrayLambdaImage red, GrayLambdaImage green, GrayLambdaImage blue);

	ColorMap scaleTo (ColorMap lambdaImage, int width, int height);

	void scanImage (ColorMap image, PixelByPixelAction action);

	ColorMap removeAlpha (ColorMap original_image);

	ColorMap scale (ColorMap image, float scaleFactor);

}
