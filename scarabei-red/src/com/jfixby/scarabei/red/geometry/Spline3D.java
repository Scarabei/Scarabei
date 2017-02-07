
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;

/** <p>
 * This is a generic 3D B-Spline class for curves of arbitrary length, control handles and patches are created and joined
 * automatically as described here: <a href="http://www.ibiblio.org/e-notes/Splines/Bint.htm">ibiblio.org/e-notes/
 * Splines/Bint.htm</a>
 * </p>
 *
 * <p>
 * Thanks to a bug report by Aaron Meyers (http://universaloscillation.com) the {@linkplain #computeVertices(int)} method has a
 * slightly changed behaviour from version 0014 onwards. In earlier versions erroneous duplicate points would be added near each
 * given control point, which lead to various weird results.
 * </p>
 *
 * <p>
 * The new behaviour of the curve interpolation/computation is described in the docs for the {@linkplain #computeVertices(int)}
 * method below.
 * </p>
 *
 * @version 0014 Added user adjustable curve tightness control */
public class Spline3D {

	public Vec3D[] points;
	public Vec3D[] delta;

	public List<Vec3D> vertices;
	public BernsteinPolynomial bernstein;

	protected Vec3D[] coeffA;
	protected double[] bi;

	protected double tightness, invTightness;

	private final int numP;

	/** @param p array of control point vectors */
	public Spline3D (final Collection<Vec3D> p) {
		this(p, null, 0.25f);
	}

	/** @param p array of control point vectors
	 * @param b predefined Bernstein polynomial (good for reusing)
	 * @param tightness default curve tightness used for the interpolated vertices {@linkplain #setTightness(double)} */
	public Spline3D (final Collection<Vec3D> p, final BernsteinPolynomial b, final double tightness) {
		this.points = new Vec3D[p.size()];
		for (int i = 0; i < p.size(); i++) {
			this.points[i] = Debug.checkNull("input[" + i + "]", p.getElementAt(i));
		}
		this.numP = this.points.length;
		this.coeffA = new Vec3D[this.numP];
		this.delta = new Vec3D[this.numP];
		this.bi = new double[this.numP];
		for (int i = 0; i < this.numP; i++) {
			this.coeffA[i] = new Vec3D();
			this.delta[i] = new Vec3D();
			this.bi[i] = 0;
		}
		this.bernstein = b;
		this.setTightness(tightness);
	}

	/** <p>
	 * Computes all curve vertices based on the resolution/number of subdivisions requested. The higher, the more vertices are
	 * computed:
	 * </p>
	 * <p>
	 * <strong>(number of control points - 1) * resolution + 1</strong>
	 * </p>
	 * <p>
	 * Since version 0014 the automatic placement of the curve handles can also be manipulated via the
	 * {@linkplain #setTightness(double)} method.
	 * </p>
	 *
	 * @param res the number of vertices to be computed per segment between original control points (incl. control point always at
	 *           the start of each segment)
	 * @return list of Vec3D vertices along the curve */
	public List<Vec3D> computeVertices (int res) {
		res++;
		if (this.bernstein == null || this.bernstein.resolution != res) {
			this.bernstein = new BernsteinPolynomial(res);
		}
		if (this.vertices == null) {
			this.vertices = Collections.newList();
		} else {
			this.vertices.clear();
		}
		this.findCPoints();
		final Vec3D deltaP = new Vec3D();
		final Vec3D deltaQ = new Vec3D();
		res--;
		for (int i = 0; i < this.numP - 1; i++) {
			final Vec3D p = this.points[i];
			final Vec3D q = this.points[i + 1];
			deltaP.set(this.delta[i]).addSelf(p);
			deltaQ.set(q).subSelf(this.delta[i + 1]);
			for (int k = 0; k < res; k++) {
				final double x = p.x * this.bernstein.b0[k] + deltaP.x * this.bernstein.b1[k] + deltaQ.x * this.bernstein.b2[k]
					+ q.x * this.bernstein.b3[k];
				final double y = p.y * this.bernstein.b0[k] + deltaP.y * this.bernstein.b1[k] + deltaQ.y * this.bernstein.b2[k]
					+ q.y * this.bernstein.b3[k];
				final double z = p.z * this.bernstein.b0[k] + deltaP.z * this.bernstein.b1[k] + deltaQ.z * this.bernstein.b2[k]
					+ q.z * this.bernstein.b3[k];
				this.vertices.add(new Vec3D(x, y, z));
			}
		}
		this.vertices.add(this.points[this.points.length - 1]);
		return this.vertices;
	}

	protected void findCPoints () {
		this.bi[1] = -this.tightness;
		this.coeffA[1].set(
			//
			(//
			this.points[2].x//
				- this.points[0].x//
				- this.delta[0].x)//
				* this.tightness,
			(this.points[2].y - this.points[0].y - this.delta[0].y) * this.tightness,
			(this.points[2].z - this.points[0].z - this.delta[0].z) * this.tightness
		//
		);
		for (int i = 2; i < this.numP - 1; i++) {
			this.bi[i] = -1 / (this.invTightness + this.bi[i - 1]);
			this.coeffA[i].set(-(this.points[i + 1].x - this.points[i - 1].x - this.coeffA[i - 1].x) * this.bi[i],
				-(this.points[i + 1].y - this.points[i - 1].y - this.coeffA[i - 1].y) * this.bi[i],
				-(this.points[i + 1].z - this.points[i - 1].z - this.coeffA[i - 1].z) * this.bi[i]);
		}
		for (int i = this.numP - 2; i > 0; i--) {
			this.delta[i].set(this.coeffA[i].x + this.delta[i + 1].x * this.bi[i],
				this.coeffA[i].y + this.delta[i + 1].y * this.bi[i], this.coeffA[i].z + this.delta[i + 1].z * this.bi[i]);
		}
	}

	/** @see #setTightness(double)
	 * @return the spline tightness
	 * @since 0014 (rev.216) */
	public double getTightness () {
		return this.tightness;
	}

	/** Sets the tightness for future curve interpolation calls. Default value is 0.25. A value of 0.0 equals linear interpolation
	 * between the given curve input points. Curve behaviour for values outside the 0.0 .. 0.5 interval is unspecified and becomes
	 * increasingly less intuitive. Negative values are possible too and create interesting results (in some cases).
	 *
	 * @param tightness the tightness value used for the next call to {@link #computeVertices(int)}
	 * @since 0014 (rev. 216) */
	public Spline3D setTightness (final double tightness) {
		this.tightness = tightness;
		this.invTightness = 1f / tightness;
		return this;
	}
}
