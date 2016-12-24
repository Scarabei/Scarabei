
package com.jfixby.scarabei.red.math;

import com.jfixby.scarabei.api.math.Angle;
import com.jfixby.scarabei.api.math.CustomAngle;
import com.jfixby.scarabei.api.math.FloatMath;

public class RedAngle implements Angle, CustomAngle {

	public static final double FLOAT_EPSILON = FloatMath.FLOAT_EPSILON();
	public static final double DOUBLE_EPSILON = FloatMath.DOUBLE_EPSILON();

	public static final double TWICE_PI = 2d * Math.PI;
	public static final double PLUS_PI = Math.PI;
	public static final double MINUS_PI = -Math.PI;
	public static final double ZERO = 0d;
	public static final double VAL_360 = 360d;
	public static final double VAL_180 = 180d;
	public static final double VAL_45 = 45d;
	public static final double VAL_30 = 30d;
	public static final double VAL_90 = 90d;
	public static final double VAL_PI_div_6 = PLUS_PI / 6d;
	public static final double VAL_PI_div_4 = PLUS_PI / 4d;
	public static final double VAL_PI_div_3 = PLUS_PI / 3d;
	public static final double VAL_PI_div_2 = PLUS_PI / 2d;

	public static final double MAX_ANGLE_VALUE = TWICE_PI;
	public static final double MIN_ANGLE_VALUE = -TWICE_PI;
	public static final double DEFAULT_ANGLE_VALUE = ZERO;

	private double magnitude;
	private int signum = 1;

	public RedAngle (double radians) {
		this.setValue(radians);
	}

	public RedAngle () {
		this(0);
	}

	public RedAngle (RedAngle other_angle) {
		this.setValue(other_angle);
	}

	public double toRadians () {
		return (signum * magnitude);
	}

	public double getMagnitude () {
		return magnitude;
	}

	public int getSignum () {
		if (this.isZero()) {
			return 0;
		}
		return this.signum;
	}

	public boolean isZero () {
		return this.isZeroRadians(magnitude);
	}

	static boolean isNegativeZero (double d) {
		return Double.compare(d, 0.0) < 0;
	}

	public CustomAngle setValue (double radians) {
		// log("...radians", radians);

		this.magnitude = radians;
		if (magnitude >= RedAngle.TWICE_PI) {
			int cycles = floor(this.magnitude / TWICE_PI);
			magnitude = this.magnitude - TWICE_PI * cycles;
			// log("cycles1", cycles);
			// log("magnitude1", magnitude);
		} else if (magnitude <= -RedAngle.TWICE_PI) {
			magnitude = -magnitude;
			int cycles = floor(this.magnitude / TWICE_PI);
			magnitude = this.magnitude - TWICE_PI * cycles;
			magnitude = -magnitude;
			// log("cycles2", cycles);
			// log("magnitude2", magnitude);
		}
		// now within [-360;360]

		if (isZeroRadians(magnitude)) {
			this.magnitude = ZERO;
			this.signum = 1;
			return this;
		} // remove zero

		// if (magnitude > Angle.MAX_ANGLE_VALUE) { // >180
		// magnitude = magnitude - Angle.MAX_ANGLE_VALUE;// cap
		// magnitude = Angle.MIN_ANGLE_VALUE + magnitude; // now within
		// // [-180;0);
		// } else if (magnitude < Angle.MIN_ANGLE_VALUE) {// <-180
		// magnitude = -magnitude;
		// magnitude = magnitude - Angle.MAX_ANGLE_VALUE;// cap
		// magnitude = Angle.MIN_ANGLE_VALUE + magnitude; // now within
		// // [-180;0);
		// magnitude = -magnitude;// now within (0;180];
		// }// now within [-180;180]-{0};

		this.signum = 1;
		if (this.magnitude < ZERO) {
			this.signum = -1;
			magnitude = -magnitude;
		}
		// now within (0;±180];

		return this;
	}

	private int floor (double d) {
		return (int)Math.floor(d);
	}

	private boolean isZeroRadians (double radians) {
		return Math.abs(radians) < FLOAT_EPSILON;
	}

	public CustomAngle setValue (Angle angle) {
		RedAngle angle_impl = (RedAngle)angle;
		this.magnitude = angle_impl.magnitude;
		this.signum = angle_impl.signum;
		return this;
	}

	@Override
	public String toString () {

		final double g = Math.toDegrees(magnitude);
		final String sg = signToString();

		final String result = sg + g + "°";

		return result;
	}

	private String signToString () {
		if (this.signum == 1) {
			return "";
		}
		return "-";
	}

	public static CustomAngle inverse (CustomAngle a, CustomAngle b) {
		b.setValue(-a.toRadians());
		return b;
	}

	public static CustomAngle plus (CustomAngle a, CustomAngle b, CustomAngle c) {

		c.setValue(a.toRadians() + b.toRadians());
		return c;

	}

	public static CustomAngle subtract (CustomAngle a, CustomAngle b, CustomAngle c) {
		CustomAngle t = inverse(b, new RedAngle());
		c = plus(a, t, c);
		return c;

	}

	public static boolean equalAngles (RedAngle A, RedAngle B) {
		return A.equals(B);
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(magnitude);
		result = prime * result + (int)(temp ^ (temp >>> 32));
		result = prime * result + signum;
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RedAngle)) {
			return false;
		}
		RedAngle other = (RedAngle)obj;
		if (Double.doubleToLongBits(magnitude) != Double.doubleToLongBits(other.magnitude)) {
			return false;
		}
		if (signum != other.signum) {
			return false;
		}
		return true;
	}

	public void makePositive () {
		this.setValue(this.toRadians() + TWICE_PI);
	}

	@Override
	public double toDegrees () {
		return FloatMath.toDegrees(this.toRadians());
	}

	@Override
	public Angle freeze () {
		return this;
	}

	@Override
	public CustomAngle newAngle () {
		return new RedAngle(this);
	}

}
