package com.jfixby.red.color;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.ColorConstant;
import com.jfixby.cmns.api.color.ColorMapping;
import com.jfixby.cmns.api.color.ColorsComponent;
import com.jfixby.cmns.api.color.ColorsSet;
import com.jfixby.cmns.api.color.CustomColor;
import com.jfixby.cmns.api.math.FloatMath;

public class RedColors implements ColorsComponent {

	private static final RedColor BLUE = new RedColor("BLUE", 0xFF0072BC);
	private static final RedColor BLACK = new RedColor("BLACK", 0xff000000);
	private static final RedColor RED = new RedColor("RED", 0xffff0000);
	private static final RedColor WHITE = new RedColor("WHITE", 0xffffffff);
	private static final RedColor BROWN = new RedColor("BROWN", 0xFFb97a57);
	private static final RedColor GREEN = new RedColor("GREEN", 0xFF8DC73F);
	private static final RedColor ORANGE = new RedColor("ORANGE", 1f,
			252 / 252f, 156 / 255f, 0f);

	private static final RedColor YELLOW = new RedColor("YELLOW", 0xFFFFFF00);

	private static final RedColor GRAY = new RedColor("GRAY", 1f, 0.5f, 0.5f,
			0.5f);

	private static final RedColor DARK_GRAY = new RedColor("DARK GRAY", 1f,
			0.25f, 0.25f, 0.25f);

	private static final RedColor LIGHT_GRAY = new RedColor("LIGHT GRAY", 1f,
			0.75f, 0.75f, 0.75f);

	private static final RedColor PURPLE = new RedColor("PURPLE", 1f, 0.5f, 0f,
			1f);
	private static final RedColor NO = new RedColor("NO", 0f, 0f, 0f, 0f);

	@Override
	public CustomColor newColor(float a, float r, float g, float b) {
		return new RedColor(a, r, g, b);
	}

	@Override
	public ColorConstant BLACK() {
		return BLACK;
	}

	@Override
	public ColorConstant RED() {
		return RED;
	}

	@Override
	public ColorConstant BLUE() {
		return BLUE;
	}

	@Override
	public ColorConstant GREEN() {
		return GREEN;
	}

	@Override
	public ColorConstant GRAY() {
		return GRAY;
	}

	@Override
	public ColorConstant WHITE() {
		return WHITE;
	}

	@Override
	public ColorConstant PURPLE() {
		return PURPLE;
	}

	@Override
	public ColorConstant NO() {
		return NO;
	}

	@Override
	public CustomColor newColor() {
		return new RedColor();
	}

	@Override
	public CustomColor newRandomColor() {
		return RedColor.random();
	}

	@Override
	public CustomColor newRandomColor(float alpha) {
		RedColor result = RedColor.random();
		result.setAlpha(alpha);
		return result;
	}

	@Override
	public CustomColor newColor(float r, float g, float b) {
		return new RedColor(r, g, b);
	}

	@Override
	public ColorConstant BROWN() {
		return BROWN;
	}

	public double distance(Color X, Color Y) {
		final double dR = FloatMath.abs(X.red() - Y.red());
		final double dG = FloatMath.abs(X.green() - Y.green());
		final double dB = FloatMath.abs(X.blue() - Y.blue());
		return FloatMath.sqrt(dR * dR + dG * dG + dB * dB);
	}

	@Override
	public CustomColor newColor(int argb) {
		return new RedColor(argb);
	}

	@Override
	public ColorsSet newColorsSet() {
		return new RedColorsSet();
	}

	@Override
	public ColorConstant YELLOW() {
		return YELLOW;
	}

	@Override
	public ColorConstant DARK_GRAY() {
		return DARK_GRAY;
	}

	@Override
	public ColorConstant LIGHT_GRAY() {
		return LIGHT_GRAY;
	}

	@Override
	public float limit(float f) {
		if (f > 1) {
			f = 1;
		}
		if (f < 0) {
			f = 0;
		}
		return f;
	}

	@Override
	public ColorConstant ORANGE() {
		return ORANGE;
	}

	@Override
	public <T> ColorMapping<T> newColorMapping() {
		throw new Error();
	}

}
