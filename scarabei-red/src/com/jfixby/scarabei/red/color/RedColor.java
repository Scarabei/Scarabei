
package com.jfixby.scarabei.red.color;

import java.util.Random;

import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.color.ColorConstant;
import com.jfixby.scarabei.api.color.Colors;
import com.jfixby.scarabei.api.color.CustomColor;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.util.md5.RSADataSecurityIncMD5;

public class RedColor implements Color, CustomColor, ColorConstant {

	final private String color_name;
	private float A = 1f;
	private float R = 1f;
	private float G;
	private float B;

	RedColor (final String color_name, final float alpha, final float red, final float green, final float blue) {
		this.color_name = color_name;
		this.set(alpha, red, green, blue);
	}

	public RedColor (final float alpha, final float red, final float green, final float blue) {
		this.set(alpha, red, green, blue);
		this.color_name = null;
	}

	public RedColor set (final float a, final float r, final float g, final float b) {
		if (a < 0 || r < 0 || g < 0 || b < 0) {
			L.e("A", a);
			L.e("R", r);
			L.e("G", g);
			L.e("B", b);
			Err.reportError("bad input");
		}
		if (a > 1f || r > 1f || g > 1f || b > 1f) {
			L.e("A", a);
			L.e("R", r);
			L.e("G", g);
			L.e("B", b);
			Err.reportError("bad input");
		}
		this.A = a;
		this.R = r;
		this.G = g;
		this.B = b;
		return this;
	}

	public RedColor (final int ARGB) {
		final int a = ((ARGB >> (8 * 3)) & 0x000000ff);
		final int r = ((ARGB >> (8 * 2)) & 0x000000ff);
		final int g = ((ARGB >> (8 * 1)) & 0x000000ff);
		final int b = ((ARGB >> (8 * 0)) & 0x000000ff);
		final float A = a / 255f;
		final float R = r / 255f;
		final float G = g / 255f;
		final float B = b / 255f;
		this.color_name = null;
		this.set(A, R, G, B);
	}

	public RedColor (final float red, final float green, final float blue) {
		this(1f, red, green, blue);
	}

	RedColor (final String name, final int ARGB) {
		this.color_name = name;
		this.setARGB(ARGB);

	}

	@Override
	public RedColor setARGB (final int ARGB) {
		final int a = ((ARGB >> (8 * 3)) & 0x000000ff);
		final int r = ((ARGB >> (8 * 2)) & 0x000000ff);
		final int g = ((ARGB >> (8 * 1)) & 0x000000ff);
		final int b = ((ARGB >> (8 * 0)) & 0x000000ff);
		final float A = a / 255f;
		final float R = r / 255f;
		final float G = g / 255f;
		final float B = b / 255f;
		this.set(A, R, G, B);

		return this;
	}

	public RedColor () {
		this.color_name = null;
	}

	public RedColor (final RedColor c) {
		this.color_name = c.color_name;
		this.setValue(c);

	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(this.A);
		result = prime * result + Float.floatToIntBits(this.B);
		result = prime * result + Float.floatToIntBits(this.G);
		result = prime * result + Float.floatToIntBits(this.R);
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RedColor)) {
			return false;
		}
		final RedColor other = (RedColor)obj;
		if (Float.floatToIntBits(this.A) != Float.floatToIntBits(other.A)) {
			return false;
		}
		if (Float.floatToIntBits(this.B) != Float.floatToIntBits(other.B)) {
			return false;
		}
		if (Float.floatToIntBits(this.G) != Float.floatToIntBits(other.G)) {
			return false;
		}
		if (Float.floatToIntBits(this.R) != Float.floatToIntBits(other.R)) {
			return false;
		}
		return true;
	}

	@Override
	public String getName () {
		if (this.color_name == null) {
			return this.toFullHexString();
		}
		return this.color_name;
	}

	@Override
	public String toFullHexString () {
		final byte b0 = (byte)(255f * this.A);
		final byte b1 = (byte)(255f * this.R);
		final byte b2 = (byte)(255f * this.G);
		final byte b3 = (byte)(255f * this.B);
		final byte[] val = new byte[] {b0, b1, b2, b3};
		final String hex = RSADataSecurityIncMD5.encodeHexString(val);
		return hex;
	}

	@Override
	public String toShortHexString () {
		final byte b1 = (byte)(255f * this.R);
		final byte b2 = (byte)(255f * this.G);
		final byte b3 = (byte)(255f * this.B);
		final byte[] val = new byte[] {b1, b2, b3};
		final String hex = RSADataSecurityIncMD5.encodeHexString(val);

		return hex;
	}

	@Override
	public CustomColor setValue (final Color other) {
		Debug.checkNull("Color", other);
		final RedColor color = (RedColor)other;
		return this.set(color.A, color.R, color.G, color.B);
	}

	@Override
	public RedColor setAlpha (final float alpha) {
		this.A = alpha;
		return this;
	}

	@Override
	public RedColor setRed (final float red) {
		this.R = red;
		return this;
	}

	@Override
	public RedColor setGreen (final float green) {
		this.G = green;
		return this;
	}

	@Override
	public RedColor setBlue (final float blue) {
		this.B = blue;
		return this;
	}

	@Override
	public float alpha () {
		return this.A;
	}

	@Override
	public float red () {
		return this.R;
	}

	@Override
	public float green () {
		return this.G;
	}

	@Override
	public float blue () {
		return this.B;
	}

	@Override
	public int toInteger () {
		final int a = (((int)(255f * this.A) << (8 * 3)) & 0xff000000);
		final int r = (((int)(255f * this.R) << (8 * 2)) & 0x00ff0000);
		final int g = (((int)(255f * this.G) << (8 * 1)) & 0x0000ff00);
		final int b = (((int)(255f * this.B) << (8 * 0)) & 0x000000ff);
		final int result = a | r | g | b;
		return result;
	}

	public static RedColor random () {
		final Random random = new Random();

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
	public String toString () {
		return "Color [" + this.getName() + "]";
	}

	@Override
	public CustomColor customize () {
		return new RedColor(this.A, this.R, this.G, this.B);
	}

	@Override
	public Color mutliply (final float f) {
		this.B = this.limit(this.B * f);
		this.R = this.limit(this.R * f);
		this.G = this.limit(this.G * f);
		return this;
	}

	private float limit (final float f) {
		return Colors.limit(f);
	}

	@Override
	public CustomColor mix (final Color other, final float proportion) {
		this.B = this.limit(this.B * (1 - proportion) + proportion * other.blue());
		this.R = this.limit(this.R * (1 - proportion) + proportion * other.red());
		this.G = this.limit(this.G * (1 - proportion) + proportion * other.green());
		return this;
	}

	@Override
	public CustomColor mix (final Color other) {
		return this.mix(other, 0.5f);
	}

	@Override
	public final float getGrayscaleValue (final float grayscale_alpha, final float grayscale_betta, final float grayscale_gamma) {
		return ((this.R * grayscale_alpha + this.G * grayscale_betta + this.B * grayscale_gamma)
			/ (grayscale_alpha + grayscale_betta + grayscale_gamma));
	}

	@Override
	public final float gray () {
		return this.getGrayscaleValue(grayscale_alpha, grayscale_betta, grayscale_gamma);
	}

	@Override
	public CustomColor invert () {
		return this.customize().setRed(1f - this.red()).setGreen(1f - this.green()).setBlue(1f - this.blue());
	}

	@Override
	public final CustomColor toGrayscale () {
		final float gray = this.gray();
		return this.customize().setRed(gray).setGreen(gray).setBlue(gray);
	}

	@Override
	final public int compareTo (final Color o) {
		return Float.compare(this.gray(), o.gray());
	}
}
