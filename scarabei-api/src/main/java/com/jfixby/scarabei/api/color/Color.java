
package com.jfixby.scarabei.api.color;

public interface Color extends Comparable<Color> {

	public static final float grayscale_alpha = 0.21f;
	public static final float grayscale_betta = 0.72f;
	public static final float grayscale_gamma = 0.07f;

	public float alpha ();

	public float red ();

	public float green ();

	public float blue ();

	public float gray ();

	public float getGrayscaleValue (float grayscale_alpha, float grayscale_betta, float grayscale_gamma);

	public int toInteger ();

	public String toFullHexString ();

	public String toShortHexString ();

	// public CustomColor multiply(float multiplier);

	public CustomColor customize ();

	CustomColor mix (Color other, float proportion);

	CustomColor mix (Color other);

	public CustomColor invert ();

	public CustomColor toGrayscale ();

}
