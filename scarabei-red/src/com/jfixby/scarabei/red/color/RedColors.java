
package com.jfixby.scarabei.red.color;

import com.jfixby.scarabei.api.color.CachedColorProjector;
import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.color.ColorConstant;
import com.jfixby.scarabei.api.color.ColorDistance;
import com.jfixby.scarabei.api.color.ColorMapping;
import com.jfixby.scarabei.api.color.ColorProjector;
import com.jfixby.scarabei.api.color.ColorRandomiser;
import com.jfixby.scarabei.api.color.ColorSet;
import com.jfixby.scarabei.api.color.Colors;
import com.jfixby.scarabei.api.color.ColorsComponent;
import com.jfixby.scarabei.api.color.CustomColor;
import com.jfixby.scarabei.api.color.GraySet;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.image.ColorMap;
import com.jfixby.scarabei.api.image.GrayMap;
import com.jfixby.scarabei.api.math.FloatMath;

public class RedColors implements ColorsComponent {

	private static final RedColor BLUE = new RedColor("BLUE", 0xFF0072BC);
	private static final RedColor FUCHSIA = new RedColor("FUCHSIA", 1, 1, 0, 1);
	private static final RedColor BLACK = new RedColor("BLACK", 0xff000000);
	private static final RedColor RED = new RedColor("RED", 0xffff0000);
	private static final RedColor WHITE = new RedColor("WHITE", 0xffffffff);
	private static final RedColor BROWN = new RedColor("BROWN", 0xFFb97a57);
	private static final RedColor GREEN = new RedColor("GREEN", 0xFF8DC73F);
	private static final RedColor ORANGE = new RedColor("ORANGE", 1f, 252 / 252f, 156 / 255f, 0f);

	private static final RedColor YELLOW = new RedColor("YELLOW", 0xFFFFFF00);

	private static final RedColor GRAY = new RedColor("GRAY", 1f, 0.5f, 0.5f, 0.5f);

	private static final RedColor DARK_GRAY = new RedColor("DARK GRAY", 1f, 0.25f, 0.25f, 0.25f);

	private static final RedColor LIGHT_GRAY = new RedColor("LIGHT GRAY", 1f, 0.75f, 0.75f, 0.75f);

	private static final RedColor PURPLE = new RedColor("PURPLE", 1f, 0.5f, 0f, 1f);
	private static final RedColor NO = new RedColor("NO", 0f, 0f, 0f, 0f);

	@Override
	public CustomColor newColor (final float a, final float r, final float g, final float b) {
		return new RedColor(a, r, g, b);
	}

	@Override
	public ColorConstant BLACK () {
		return BLACK;
	}

	@Override
	public ColorConstant RED () {
		return RED;
	}

	@Override
	public ColorConstant BLUE () {
		return BLUE;
	}

	@Override
	public ColorConstant GREEN () {
		return GREEN;
	}

	@Override
	public ColorConstant GRAY () {
		return GRAY;
	}

	@Override
	public ColorConstant WHITE () {
		return WHITE;
	}

	@Override
	public ColorConstant PURPLE () {
		return PURPLE;
	}

	@Override
	public ColorConstant NO () {
		return NO;
	}

	@Override
	public CustomColor newColor () {
		return new RedColor();
	}

	@Override
	public CustomColor newRandomColor () {
		return RedColor.random();
	}

	@Override
	public CustomColor newRandomColor (final float alpha) {
		final RedColor result = RedColor.random();
		result.setAlpha(alpha);
		return result;
	}

	@Override
	public CustomColor newColor (final float r, final float g, final float b) {
		return new RedColor(r, g, b);
	}

	@Override
	public ColorConstant BROWN () {
		return BROWN;
	}

	final ColorDistance distanceRGB = new ColorDistance() {

		@Override
		public float measure (final Color X, final Color Y) {
			final double dR = FloatMath.component().abs(X.red() - Y.red());
			final double dG = FloatMath.component().abs(X.green() - Y.green());
			final double dB = FloatMath.component().abs(X.blue() - Y.blue());
			return (float)FloatMath.component().sqrt(dR * dR + dG * dG + dB * dB);
		}

	};

	@Override
	public CustomColor newColor (final int argb) {
		return new RedColor(argb);
	}

	@Override
	public ColorSet newColorSet () {
		return new RedColorsSet(Colors.distanceRGB());
	}

	@Override
	public ColorConstant YELLOW () {
		return YELLOW;
	}

	@Override
	public ColorConstant DARK_GRAY () {
		return DARK_GRAY;
	}

	@Override
	public ColorConstant LIGHT_GRAY () {
		return LIGHT_GRAY;
	}

	@Override
	public float limit (float f) {
		if (f > 1) {
			f = 1;
		}
		if (f < 0) {
			f = 0;
		}
		return f;
	}

	@Override
	public ColorConstant ORANGE () {
		return ORANGE;
	}

	@Override
	public <T> ColorMapping<T> newColorMapping () {
		Err.throwNotImplementedYet();
		return null;
	}

	@Override
	public CustomColor newGray (final float gray) {
		return this.newColor(gray, gray, gray);
	}

	@Override
	public ColorRandomiser newColorRandomizer (final long seed) {
		return new RedColorRandomizer(this, seed);
	}

	@Override
	public Color FUCHSIA () {
		return FUCHSIA;
	}

	@Override
	public Color newColor (String hexstring) {
		hexstring = hexstring.replaceAll("#", "");
		final int intColr = (int)Long.parseLong(hexstring, 16);
		return new RedColor(hexstring, intColr);
	}

	@Override
	public GraySet newGraySet () {
		return new RedGraySet();
	}

	@Override
	public GraySet newGraySet (final float... array) {
		return new RedGraySet().addAll(array);
	}

	@Override
	public GraySet newUniformGraySet (final int depth) {
		Debug.component().checkTrue("depth > 0", depth > 0);
		final RedGraySet set = new RedGraySet();
		final float delta = (depth - 1);
		for (int i = 0; i < depth; i++) {
			final float value = i / delta;
			set.add(value);
		}
		return set;
	}

	@Override
	public ColorSet newColorsSet (final ColorMap image) {
		final ColorSet palette = this.newColorSet();
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				final Color color = image.valueAt(i, j);
				palette.add(color);
			}
		}
		return palette;
	}

	@Override
	public ColorDistance distanceRGB () {
		return this.distanceRGB;
	}

	@Override
	public CachedColorProjector colorProjectorCache (final ColorProjector input) {
		return new RedCachedColorProjector(input);
	}

	@Override
	public GraySet newGraySet (final GrayMap image) {
		final GraySet palette = this.newGraySet();
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				palette.add(image.valueAt(i, j));
			}
		}
		return palette;
	}

}
