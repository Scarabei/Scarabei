package com.jfixby.red.color;

import java.util.Random;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.ColorConstant;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.color.CustomColor;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.util.md5.AlpaeroMD5;

public class RedColor implements Color, CustomColor, ColorConstant {

	final private String color_name;
	private float A = 1f;
	private float R = 1f;
	private float G;
	private float B;

	RedColor(String color_name, float alpha, float red, float green, float blue) {
		this.color_name = color_name;
		set(alpha, red, green, blue);
	}

	public RedColor(float alpha, float red, float green, float blue) {
		set(alpha, red, green, blue);
		this.color_name = null;
	}

	public RedColor set(float a, float r, float g, float b) {
		if (a < 0 || r < 0 || g < 0 || b < 0) {
			L.e("A", a);
			L.e("R", r);
			L.e("G", g);
			L.e("B", b);
			throw new Error();
		}
		if (a > 1f || r > 1f || g > 1f || b > 1f) {
			L.e("A", a);
			L.e("R", r);
			L.e("G", g);
			L.e("B", b);
			throw new Error();
		}
		A = a;
		R = r;
		G = g;
		B = b;
		return this;
	}

	public RedColor(int ARGB) {
		int a = ((ARGB >> (8 * 3)) & 0x000000ff);
		int r = ((ARGB >> (8 * 2)) & 0x000000ff);
		int g = ((ARGB >> (8 * 1)) & 0x000000ff);
		int b = ((ARGB >> (8 * 0)) & 0x000000ff);
		float A = a / 255f;
		float R = r / 255f;
		float G = g / 255f;
		float B = b / 255f;
		color_name = null;
		set(A, R, G, B);
	}

	public RedColor(float red, float green, float blue) {
		this(1f, red, green, blue);
	}

	RedColor(String name, int ARGB) {
		color_name = name;
		setARGB(ARGB);

	}

	public RedColor setARGB(int ARGB) {
		int a = ((ARGB >> (8 * 3)) & 0x000000ff);
		int r = ((ARGB >> (8 * 2)) & 0x000000ff);
		int g = ((ARGB >> (8 * 1)) & 0x000000ff);
		int b = ((ARGB >> (8 * 0)) & 0x000000ff);
		float A = a / 255f;
		float R = r / 255f;
		float G = g / 255f;
		float B = b / 255f;
		set(A, R, G, B);

		return this;
	}

	public RedColor() {
		color_name = null;
	}

	public RedColor(RedColor c) {
		color_name = c.color_name;
		this.setValue(c);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(A);
		result = prime * result + Float.floatToIntBits(B);
		result = prime * result + Float.floatToIntBits(G);
		result = prime * result + Float.floatToIntBits(R);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RedColor)) {
			return false;
		}
		RedColor other = (RedColor) obj;
		if (Float.floatToIntBits(A) != Float.floatToIntBits(other.A)) {
			return false;
		}
		if (Float.floatToIntBits(B) != Float.floatToIntBits(other.B)) {
			return false;
		}
		if (Float.floatToIntBits(G) != Float.floatToIntBits(other.G)) {
			return false;
		}
		if (Float.floatToIntBits(R) != Float.floatToIntBits(other.R)) {
			return false;
		}
		return true;
	}

	@Override
	public String getName() {
		if (color_name == null) {
			return this.toFullHexString();
		}
		return color_name;
	}

	@Override
	public String toFullHexString() {
		byte b0 = (byte) (255f * A);
		byte b1 = (byte) (255f * R);
		byte b2 = (byte) (255f * G);
		byte b3 = (byte) (255f * B);
		byte[] val = new byte[] { b0, b1, b2, b3 };
		String hex = AlpaeroMD5.encodeHexString(val);
		return hex;
	}

	@Override
	public String toShortHexString() {
		byte b1 = (byte) (255f * R);
		byte b2 = (byte) (255f * G);
		byte b3 = (byte) (255f * B);
		byte[] val = new byte[] { b1, b2, b3 };
		String hex = AlpaeroMD5.encodeHexString(val);

		return hex;
	}

	@Override
	public CustomColor setValue(Color other) {
		JUtils.checkNull("Color", other);
		RedColor color = (RedColor) other;
		return this.set(color.A, color.R, color.G, color.B);
	}

	@Override
	public RedColor setAlpha(float alpha) {
		this.A = alpha;
		return this;
	}

	@Override
	public RedColor setRed(float red) {
		this.R = red;
		return this;
	}

	@Override
	public RedColor setGreen(float green) {
		this.G = green;
		return this;
	}

	@Override
	public RedColor setBlue(float blue) {
		this.B = blue;
		return this;
	}

	@Override
	public float alpha() {
		return A;
	}

	@Override
	public float red() {
		return R;
	}

	@Override
	public float green() {
		return G;
	}

	@Override
	public float blue() {
		return B;
	}

	@Override
	public float getGrayscaleValue() {
		return (R + G + B) * A / 3f;
	}

	@Override
	public int toInteger() {
		int a = (((int) (255f * A) << (8 * 3)) & 0xff000000);
		int r = (((int) (255f * R) << (8 * 2)) & 0x00ff0000);
		int g = (((int) (255f * G) << (8 * 1)) & 0x0000ff00);
		int b = (((int) (255f * B) << (8 * 0)) & 0x000000ff);
		int result = a | r | g | b;
		return result;
	}

	public static RedColor random() {
		Random random = new Random();

		final float r = random.nextFloat();
		final float g = random.nextFloat();
		final float b = random.nextFloat();
		return new RedColor(r, g, b);
	}

	// @Override
	// public CustomColor multiply(float multiplier) {
	// return new RedColor(this.A, this.R * multiplier, this.G * multiplier,
	// this.B * multiplier);
	// }

	@Override
	public String toString() {
		return "Color [" + color_name + "]";
	}

	@Override
	public CustomColor customize() {
		return new RedColor(this.A, this.R, this.G, this.B);
	}

	@Override
	public Color mutliply(float f) {
		this.B = limit(this.B * f);
		this.R = limit(this.R * f);
		this.G = limit(this.G * f);
		return this;
	}

	private float limit(float f) {
		return Colors.limit(f);
	}

	@Override
	public CustomColor mix(Color other, float proportion) {
		this.B = limit(this.B * (1 - proportion) + proportion * other.blue());
		this.R = limit(this.R * (1 - proportion) + proportion * other.red());
		this.G = limit(this.G * (1 - proportion) + proportion * other.green());
		return this;
	}

	@Override
	public CustomColor mix(Color other) {
		return this.mix(other, 0.5f);
	}

}
