
package com.jfixby.scarabei.api.image;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.color.ColorProjector;
import com.jfixby.scarabei.api.color.GraySet;

public class ImageProcessing {

	public static final String IMAGE_CHANNEL_FILE_EXTENTION = ".r3-img-gray";

	static private ComponentInstaller<ImageProcessingComponent> componentInstaller = new ComponentInstaller<ImageProcessingComponent>(
		"ImageProcessing");

	public static final void installComponent (final ImageProcessingComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final ImageProcessingComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final ImageProcessingComponent component () {
		return componentInstaller.getComponent();
	}

	public static ArrayColorMap newArrayColorMap (final ArrayColorMapSpecs color_function_specs) {
		return invoke().newArrayColorMap(color_function_specs);
	}

	public static ArrayColorMapSpecs newArrayColorMapSpecs () {
		return invoke().newArrayColorMapSpecs();
	}

	public static ColorMapSpecs newColorMapSpecs () {
		return invoke().newColorMapSpecs();
	}

	public static ColorMap newColorMap (final ColorMapSpecs lambda_specs) {
		return invoke().newColorMap(lambda_specs);
	}

	public static ColorMap newColorMap (final ColoredLambdaImage lambda, final int width, final int height) {
		return invoke().newColorMap(lambda, width, height);
	}

	public static ColoredLambdaImageCache newImageCache (final int width, final int height) {
		return invoke().newImageCache(width, height);
	}

	public static GrayLambdaImage scale (final GrayLambdaImage base, final float scaleX, final float scaleY) {
		return invoke().scale(base, scaleX, scaleY);
	}

	public static ColoredLambdaImage scale (final ColoredLambdaImage base, final float scaleX, final float scaleY) {
		return invoke().scale(base, scaleX, scaleY);
	}

	public static GrayLambdaImage minus (final GrayLambdaImage base, final GrayLambdaImage diff) {
		return invoke().minus(base, diff);
	}

	public static GrayLambdaImage multiply (final GrayLambdaImage image, final float mult) {
		return invoke().multiply(image, mult);
	}

	public static GrayMap newGrayMap (final GrayLambdaImage lambda, final int width, final int height) {
		return invoke().newGrayMap(lambda, width, height);
	}

	public static GrayMapSpecs newGrayMapSpecs () {
		return invoke().newGrayMapSpecs();
	}

	public static GrayLambdaImage roundArguments (final GrayLambdaImage image) {
		return invoke().roundArguments(image);
	}

	public static ArrayGrayMapSpecs newArrayGrayMapSpecs () {
		return invoke().newArrayGrayMapSpecs();
	}

	public static ArrayGrayMap newArrayGrayMap (final ArrayGrayMapSpecs specs) {
		return invoke().newArrayGrayMap(specs);
	}

	public static GrayLambdaImage plus (final GrayLambdaImage base, final GrayLambdaImage add) {
		return invoke().plus(base, add);
	}

	public static int roundArgument (final float x) {
		return invoke().roundArgument(x);
	}

	public static GrayLambdaImage ONE () {
		return invoke().ONE();
	}

	public static GrayLambdaImage ZERO () {
		return invoke().ZERO();
	}

	public static IndexedColorMapSpecs newIndexedColorMapSpecs () {
		return invoke().newIndexedColorMapSpecs();
	}

	public static GrayIndexedLambdaImage index (final GrayLambdaImage lambdaImage, final GraySet palette) {
		return invoke().index(lambdaImage, palette);
	}

	public static ColoredLambdaImage index (final ColoredLambdaImage lambdaImage, final ColorProjector palette) {
		return invoke().index(lambdaImage, palette);
	}

	public static ColorMap newColorMap (final int width, final int height, final GrayLambdaImage alpha, final GrayLambdaImage red,
		final GrayLambdaImage green, final GrayLambdaImage blue) {
		return invoke().newColorMap(width, height, alpha, red, green, blue);
	}

	public static ColoredLambdaImage merge (final GrayLambdaImage alpha, final GrayLambdaImage red, final GrayLambdaImage green,
		final GrayLambdaImage blue) {
		return invoke().merge(alpha, red, green, blue);
	}

	public static ColorMap scaleTo (final ColorMap lambdaImage, final int width, final int height) {
		return invoke().scaleTo(lambdaImage, width, height);
	}

	public static void scanImage (final ColorMap image, final PixelByPixelAction action) {
		invoke().scanImage(image, action);
	}

	public static ColorMap removeAlpha (final ColorMap original_image) {
		return invoke().removeAlpha(original_image);
	}

	public static ColorMap scale (final ColorMap image, final float scaleFactor) {
		return invoke().scale(image, scaleFactor);
	}

}
