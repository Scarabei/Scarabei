package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.ColorProjector;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.color.GraySet;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.ArrayGrayMap;
import com.jfixby.cmns.api.image.ArrayGrayMapSpecs;
import com.jfixby.cmns.api.image.ColorMap;
import com.jfixby.cmns.api.image.ColorMapSpecs;
import com.jfixby.cmns.api.image.ColoredλImage;
import com.jfixby.cmns.api.image.ColoredλImageCache;
import com.jfixby.cmns.api.image.GrayIndexedλImage;
import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.image.GrayMapSpecs;
import com.jfixby.cmns.api.image.GrayλImage;
import com.jfixby.cmns.api.image.ImageProcessing;
import com.jfixby.cmns.api.image.ImageProcessingComponent;
import com.jfixby.cmns.api.image.IndexedColorMapSpecs;
import com.jfixby.cmns.api.image.PixelByPixelAction;

public class RedImageProcessing implements ImageProcessingComponent {

    private static final GrayλImage ONE = new GrayλImage() {
	@Override
	public float valueAt(float x, float y) {
	    return 1;
	}
    };
    private static final GrayλImage ZERO = new GrayλImage() {
	@Override
	public float valueAt(float x, float y) {
	    return 0;
	}
    };

    @Override
    public ArrayColorMap newArrayColorMap(ArrayColorMapSpecs color_function_specs) {
	return new RedArrayColorMap(color_function_specs);
    }

    @Override
    public ArrayColorMapSpecs newArrayColorMapSpecs() {
	return new RedArrayColorMapSpecs();
    }

    @Override
    public ColorMapSpecs newColorMapSpecs() {
	return new RedColorMapSpecs();
    }

    @Override
    public ColoredλImageCache newImageCache(int width, int height) {
	return new RedColoredλImageCache(width, height);
    }

    @Override
    public ColorMap newColorMap(ColoredλImage lambda, int width, int height) {
	return new RedColorMap(lambda, width, height);
    }

    @Override
    public GrayλImage scale(final GrayλImage base, final float scaleX, final float scaleY) {
	return new GrayλImage() {
	    @Override
	    public float valueAt(float x, float y) {
		return base.valueAt(x / scaleX, y / scaleY);
	    }
	};
    }

    @Override
    public GrayλImage minus(final GrayλImage base, final GrayλImage diff) {
	return new GrayλImage() {
	    @Override
	    public float valueAt(float x, float y) {
		return base.valueAt(x, y) - diff.valueAt(x, y);
	    }
	};
    }

    @Override
    public GrayλImage multiply(final GrayλImage image, final float mult) {
	return new GrayλImage() {
	    @Override
	    public float valueAt(float x, float y) {
		return image.valueAt(x, y) * mult;
	    }
	};
    }

    @Override
    public GrayMap newGrayMap(GrayλImage lambda, int width, int height) {
	return new RedGrayMap(lambda, width, height);
    }

    @Override
    public GrayMapSpecs newGrayMapSpecs() {
	return new RedGrayMapSpecs();
    }

    @Override
    public GrayλImage roundArguments(final GrayλImage image) {
	return new GrayλImage() {
	    @Override
	    public float valueAt(float x, float y) {
		// return image.valueAt(FloatMath.round(x), FloatMath.round(y));
		// return image.valueAt(FloatMath.floorDown(x),
		// FloatMath.floorDown(y));
		return image.valueAt(roundArgument(x), roundArgument(y));
		// return image.valueAt(x, y);
	    }

	};
    }

    @Override
    public final int roundArgument(final float x) {
	return (int) x;
	// return (int) FloatMath.component().floorUp(x);
	// return (int) FloatMath.component().round(x + 0.5f);
    }

    @Override
    public ArrayGrayMapSpecs newArrayGrayMapSpecs() {
	return new RedArrayGrayMapSpecs();
    }

    @Override
    public ArrayGrayMap newArrayGrayMap(ArrayGrayMapSpecs specs) {
	return new RedArrayGrayMap(specs);
    }

    @Override
    public GrayλImage plus(final GrayλImage base, final GrayλImage add) {
	return new GrayλImage() {
	    @Override
	    public float valueAt(float x, float y) {
		return base.valueAt(x, y) + add.valueAt(x, y);
	    }
	};
    }

    @Override
    public GrayλImage ONE() {
	return ONE;
    }

    @Override
    public GrayλImage ZERO() {
	return ZERO;
    }

    @Override
    public ColoredλImage scale(final ColoredλImage base, final float scaleX, final float scaleY) {
	return new ColoredλImage() {
	    @Override
	    public Color valueAt(float x, float y) {
		return base.valueAt(x / scaleX, y / scaleY);
	    }
	};
    }

    @Override
    public IndexedColorMapSpecs newIndexedColorMapSpecs() {
	return new RedIndexedColorMapSpecs();
    }

    @Override
    public GrayIndexedλImage index(final GrayλImage base, final GraySet palette) {
	return new GrayIndexedλImage() {
	    @Override
	    public float valueAt(float x, float y) {
		return palette.findClosest(base.valueAt(x, y));
	    }

	    @Override
	    public GraySet getPalette() {
		return palette;
	    }
	};
    }

    @Override
    public ColoredλImage index(final ColoredλImage lambdaImage, final ColorProjector palette) {
	return new ColoredλImage() {
	    @Override
	    public Color valueAt(float x, float y) {
		return palette.findClosest(lambdaImage.valueAt(x, y));
	    }

	};
    }

    @Override
    public ColorMap newColorMap(int width, int height, GrayλImage alpha, GrayλImage red, GrayλImage green,
	    GrayλImage blue) {
	ColoredλImage lambda = merge(alpha, red, green, blue);

	return new RedColorMap(lambda, width, height);
    }

    final static private GrayλImage defaultAlpha(GrayλImage alpha) {
	if (alpha == null) {
	    return ImageProcessing.ONE();
	}
	return alpha;
    }

    @Override
    public ColoredλImage merge(GrayλImage c_alpha, GrayλImage c_red, GrayλImage c_green, GrayλImage c_blue) {
	final GrayλImage green = Debug.checkNull(c_green);
	final GrayλImage red = Debug.checkNull(c_red);
	final GrayλImage blue = Debug.checkNull(c_blue);
	final GrayλImage alpha = defaultAlpha(c_alpha);

	final ColoredλImage lambda = new ColoredλImage() {
	    @Override
	    public Color valueAt(final float x, final float y) {
		float a = alpha.valueAt(x, y);
		float r = red.valueAt(x, y);
		float g = green.valueAt(x, y);
		float b = blue.valueAt(x, y);
		return Colors.newColor(a, r, g, b);
	    }
	};
	return lambda;
    }

    @Override
    public ColorMap newColorMap(ColorMapSpecs lambda_specs) {
	if (lambda_specs.getLambdaColoredImage() != null) {
	    return new RedColorMap(lambda_specs.getLambdaColoredImage(), lambda_specs.getColorMapWidth(),
		    lambda_specs.getColorMapHeight());
	}

	final GrayλImage green = Debug.checkNull(lambda_specs.getGreen());
	final GrayλImage red = Debug.checkNull(lambda_specs.getRed());
	final GrayλImage blue = Debug.checkNull(lambda_specs.getBlue());
	final GrayλImage alpha = defaultAlpha(lambda_specs.getAlpha());
	final ColoredλImage lambda = merge(alpha, red, green, blue);
	return new RedColorMap(lambda, lambda_specs.getColorMapWidth(), lambda_specs.getColorMapHeight());
    }

    @Override
    public ColorMap scaleTo(ColorMap image, int width, int height) {
	return this.newColorMap(this.scale(image, (width * 1f / image.getWidth()), (1f * height / image.getHeight())),
		width, height);
    }

    @Override
    public void scanImage(ColorMap image, PixelByPixelAction action) {
	final int W = image.getWidth();
	final int H = image.getHeight();
	for (int y = 0; y < H; y++) {
	    for (int x = 0; x < W; x++) {
		final Color value = image.valueAt(x, y);
		if (action.scanPixelAt(image, x, y, value)) {
		    return;
		}
	    }
	}
    }

    @Override
    public ColorMap removeAlpha(ColorMap original_image) {
	return this.newColorMap(original_image.getWidth(), original_image.getHeight(), ONE, original_image.getRed(),
		original_image.getGreen(), original_image.getBlue());

    }

}
