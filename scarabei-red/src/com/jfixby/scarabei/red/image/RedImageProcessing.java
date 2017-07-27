
package com.jfixby.scarabei.red.image;

import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.color.ColorProjector;
import com.jfixby.scarabei.api.color.Colors;
import com.jfixby.scarabei.api.color.GraySet;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.image.ArrayColorMap;
import com.jfixby.scarabei.api.image.ArrayColorMapSpecs;
import com.jfixby.scarabei.api.image.ArrayGrayMap;
import com.jfixby.scarabei.api.image.ArrayGrayMapSpecs;
import com.jfixby.scarabei.api.image.ColorMap;
import com.jfixby.scarabei.api.image.ColorMapSpecs;
import com.jfixby.scarabei.api.image.ColoredλImage;
import com.jfixby.scarabei.api.image.ColoredλImageCache;
import com.jfixby.scarabei.api.image.GrayIndexedλImage;
import com.jfixby.scarabei.api.image.GrayMap;
import com.jfixby.scarabei.api.image.GrayMapSpecs;
import com.jfixby.scarabei.api.image.GrayλImage;
import com.jfixby.scarabei.api.image.ImageProcessing;
import com.jfixby.scarabei.api.image.ImageProcessingComponent;
import com.jfixby.scarabei.api.image.IndexedColorMapSpecs;
import com.jfixby.scarabei.api.image.PixelByPixelAction;

public class RedImageProcessing implements ImageProcessingComponent {

	private static final GrayλImage ONE = new GrayλImage() {
		@Override
		public float valueAt (final float x, final float y) {
			return 1;
		}
	};
	private static final GrayλImage ZERO = new GrayλImage() {
		@Override
		public float valueAt (final float x, final float y) {
			return 0;
		}
	};

	@Override
	public ArrayColorMap newArrayColorMap (final ArrayColorMapSpecs color_function_specs) {
		return new RedArrayColorMap(color_function_specs);
	}

	@Override
	public ArrayColorMapSpecs newArrayColorMapSpecs () {
		return new RedArrayColorMapSpecs();
	}

	@Override
	public ColorMapSpecs newColorMapSpecs () {
		return new RedColorMapSpecs();
	}

	@Override
	public ColoredλImageCache newImageCache (final int width, final int height) {
		return new RedColoredλImageCache(width, height);
	}

	@Override
	public ColorMap newColorMap (final ColoredλImage lambda, final int width, final int height) {
		return new RedColorMap(lambda, width, height);
	}

	@Override
	public GrayλImage scale (final GrayλImage base, final float scaleX, final float scaleY) {
		return new GrayλImage() {
			@Override
			public float valueAt (final float x, final float y) {
				return base.valueAt(x / scaleX, y / scaleY);
			}
		};
	}

	@Override
	public GrayλImage minus (final GrayλImage base, final GrayλImage diff) {
		return new GrayλImage() {
			@Override
			public float valueAt (final float x, final float y) {
				return base.valueAt(x, y) - diff.valueAt(x, y);
			}
		};
	}

	@Override
	public GrayλImage multiply (final GrayλImage image, final float mult) {
		return new GrayλImage() {
			@Override
			public float valueAt (final float x, final float y) {
				return image.valueAt(x, y) * mult;
			}
		};
	}

	@Override
	public GrayMap newGrayMap (final GrayλImage lambda, final int width, final int height) {
		return new RedGrayMap(lambda, width, height);
	}

	@Override
	public GrayMapSpecs newGrayMapSpecs () {
		return new RedGrayMapSpecs();
	}

	@Override
	public GrayλImage roundArguments (final GrayλImage image) {
		return new GrayλImage() {
			@Override
			public float valueAt (final float x, final float y) {
				// return image.valueAt(FloatMath.round(x), FloatMath.round(y));
				// return image.valueAt(FloatMath.floorDown(x),
				// FloatMath.floorDown(y));
				return image.valueAt(RedImageProcessing.this.roundArgument(x), RedImageProcessing.this.roundArgument(y));
				// return image.valueAt(x, y);
			}

		};
	}

	@Override
	public final int roundArgument (final float x) {
		return (int)x;
		// return (int) FloatMath.component().floorUp(x);
		// return (int) FloatMath.component().round(x + 0.5f);
	}

	@Override
	public ArrayGrayMapSpecs newArrayGrayMapSpecs () {
		return new RedArrayGrayMapSpecs();
	}

	@Override
	public ArrayGrayMap newArrayGrayMap (final ArrayGrayMapSpecs specs) {
		return new RedArrayGrayMap(specs);
	}

	@Override
	public GrayλImage plus (final GrayλImage base, final GrayλImage add) {
		return new GrayλImage() {
			@Override
			public float valueAt (final float x, final float y) {
				return base.valueAt(x, y) + add.valueAt(x, y);
			}
		};
	}

	@Override
	public GrayλImage ONE () {
		return ONE;
	}

	@Override
	public GrayλImage ZERO () {
		return ZERO;
	}

	@Override
	public ColoredλImage scale (final ColoredλImage base, final float scaleX, final float scaleY) {
		return new ColoredλImage() {
			@Override
			public Color valueAt (final float x, final float y) {
				return base.valueAt(x / scaleX, y / scaleY);
			}
		};
	}

	@Override
	public IndexedColorMapSpecs newIndexedColorMapSpecs () {
		return new RedIndexedColorMapSpecs();
	}

	@Override
	public GrayIndexedλImage index (final GrayλImage base, final GraySet palette) {
		return new GrayIndexedλImage() {
			@Override
			public float valueAt (final float x, final float y) {
				return palette.findClosest(base.valueAt(x, y));
			}

			@Override
			public GraySet getPalette () {
				return palette;
			}
		};
	}

	@Override
	public ColoredλImage index (final ColoredλImage lambdaImage, final ColorProjector palette) {
		return new ColoredλImage() {
			@Override
			public Color valueAt (final float x, final float y) {
				return palette.findClosest(lambdaImage.valueAt(x, y));
			}

		};
	}

	@Override
	public ColorMap newColorMap (final int width, final int height, final GrayλImage alpha, final GrayλImage red,
		final GrayλImage green, final GrayλImage blue) {
		final ColoredλImage lambda = this.merge(alpha, red, green, blue);

		return new RedColorMap(lambda, width, height);
	}

	final static private GrayλImage defaultAlpha (final GrayλImage alpha) {
		if (alpha == null) {
			return ImageProcessing.ONE();
		}
		return alpha;
	}

	@Override
	public ColoredλImage merge (final GrayλImage c_alpha, final GrayλImage c_red, final GrayλImage c_green,
		final GrayλImage c_blue) {
		final GrayλImage green = Debug.checkNull(c_green);
		final GrayλImage red = Debug.checkNull(c_red);
		final GrayλImage blue = Debug.checkNull(c_blue);
		final GrayλImage alpha = defaultAlpha(c_alpha);

		final ColoredλImage lambda = new ColoredλImage() {
			@Override
			public Color valueAt (final float x, final float y) {
				final float a = alpha.valueAt(x, y);
				final float r = red.valueAt(x, y);
				final float g = green.valueAt(x, y);
				final float b = blue.valueAt(x, y);
				return Colors.newColor(a, r, g, b);
			}
		};
		return lambda;
	}

	@Override
	public ColorMap newColorMap (final ColorMapSpecs lambda_specs) {
		if (lambda_specs.getLambdaColoredImage() != null) {
			return new RedColorMap(lambda_specs.getLambdaColoredImage(), lambda_specs.getColorMapWidth(),
				lambda_specs.getColorMapHeight());
		}

		final GrayλImage green = Debug.checkNull(lambda_specs.getGreen());
		final GrayλImage red = Debug.checkNull(lambda_specs.getRed());
		final GrayλImage blue = Debug.checkNull(lambda_specs.getBlue());
		final GrayλImage alpha = defaultAlpha(lambda_specs.getAlpha());
		final ColoredλImage lambda = this.merge(alpha, red, green, blue);
		return new RedColorMap(lambda, lambda_specs.getColorMapWidth(), lambda_specs.getColorMapHeight());
	}

	@Override
	public ColorMap scaleTo (final ColorMap image, final int width, final int height) {
		return this.newColorMap(this.scale(image, (width * 1f / image.getWidth()), (1f * height / image.getHeight())), width,
			height);
	}

	@Override
	public void scanImage (final ColorMap image, final PixelByPixelAction action) {
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
	public ColorMap removeAlpha (final ColorMap original_image) {
		return this.newColorMap(original_image.getWidth(), original_image.getHeight(), ONE, original_image.getRed(),
			original_image.getGreen(), original_image.getBlue());

	}

	@Override
	public ColorMap scale (final ColorMap image, final float scaleFactor) {
		int width = (int)(image.getWidth() * scaleFactor);
		int height = (int)(image.getHeight() * scaleFactor);
		if (width == 0) {
			width = 1;
		}
		if (height == 0) {
			height = 1;
		}
		return this.scaleTo(image, width, height);
	}

}
