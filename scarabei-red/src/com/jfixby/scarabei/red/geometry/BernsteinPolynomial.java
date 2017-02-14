
package com.jfixby.scarabei.red.geometry;

/** Helper class for the spline classes in this package. Used to compute subdivision points of the curve.
 *
 * @author toxi */
public class BernsteinPolynomial {
	public float[] b0, b1, b2, b3;
	public int resolution;

	/** @param res number of subdivision steps between each control point of the spline */
	public BernsteinPolynomial (final int res) {
		this.resolution = res;
		this.b0 = new float[res];
		this.b1 = new float[res];
		this.b2 = new float[res];
		this.b3 = new float[res];
		float t = 0;
		final float dt = 1.0f / (this.resolution - 1);
		for (int i = 0; i < this.resolution; i++) {
			final float t1 = 1 - t;
			final float t12 = t1 * t1;
			final float t2 = t * t;
			this.b0[i] = t1 * t12;
			this.b1[i] = 3 * t * t12;
			this.b2[i] = 3 * t2 * t1;
			this.b3[i] = t * t2;
			t += dt;
		}
	}
}
