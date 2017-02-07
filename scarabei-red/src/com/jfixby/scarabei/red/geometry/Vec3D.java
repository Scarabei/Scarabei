
package com.jfixby.scarabei.red.geometry;

/** Comprehensive 3D vector class with additional basic intersection and collision detection features.
 *
 * @author Karsten Schmidt */
public class Vec3D {

	/** X coordinate */
	public double x;

	/** Y coordinate */
	public double y;

	/** Z coordinate */
	public double z;

	/** Creates a new zero vector */
	public Vec3D () {
		this.x = this.y = this.z = 0;
	}

	/** Creates a new vector with the given coordinates
	 *
	 * @param x
	 * @param y
	 * @param z */
	public Vec3D (final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/** Overrides coordinates with the given values
	 *
	 * @param x
	 * @param y
	 * @param z
	 * @return itself */
	public Vec3D set (final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	/** Overrides coordinates with the ones of the given vector
	 *
	 * @param v vector to be copied
	 * @return itself */
	public Vec3D set (final Vec3D v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
	}

	/** Subtracts vector {a,b,c} and overrides coordinates with result.
	 *
	 * @param a X coordinate
	 * @param b Y coordinate
	 * @param c Z coordinate
	 * @return itself */
	public final Vec3D subSelf (final double a, final double b, final double c) {
		this.x -= a;
		this.y -= b;
		this.z -= c;
		return this;
	}

	/** Subtracts vector v and overrides coordinates with result.
	 *
	 * @param v vector to be subtracted
	 * @return itself */
	public final Vec3D subSelf (final Vec3D v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	/** Adds vector {a,b,c} and overrides coordinates with result.
	 *
	 * @param a X coordinate
	 * @param b Y coordinate
	 * @param c Z coordinate
	 * @return itself */
	public final Vec3D addSelf (final double a, final double b, final double c) {
		this.x += a;
		this.y += b;
		this.z += c;
		return this;
	}

	/** Adds vector v and overrides coordinates with result.
	 *
	 * @param v vector to add
	 * @return itself */
	public final Vec3D addSelf (final Vec3D v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}

}
