
package com.jfixby.scarabei.red.math;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.math.Average;
import com.jfixby.scarabei.api.math.FloatMathComponent;

public abstract class RedFloatMath implements FloatMathComponent {

	public final double TWICE_PI = 2d * java.lang.Math.PI;
	public final double PLUS_PI = java.lang.Math.PI;
	public final double MINUS_PI = -java.lang.Math.PI;

	public final int POSSIBLE_NUMBER_OF_SAFE_FLOAT_OPERATIONS = 10000;

	public final double JAVA_FLOAT_PRECISION = this.power(2, -25);
	public final double JAVA_DOUBLE_PRECISION = this.power(2, -53);

	// f1==f2 <=> |f1-f2|<FLOAT_EPSILON
	public final double FLOAT_EPSILON//
		= this.JAVA_FLOAT_PRECISION * this.POSSIBLE_NUMBER_OF_SAFE_FLOAT_OPERATIONS;

	// distinguish floats from doubles
	// f1==f2 but d1!=d2
	public final double EPSILON//
		= this.JAVA_FLOAT_PRECISION / 2d;

	// d1==d2 <=> |d1-d2|<DOUBLE_EPSILON
	public final double DOUBLE_EPSILON//
		= this.JAVA_DOUBLE_PRECISION * this.POSSIBLE_NUMBER_OF_SAFE_FLOAT_OPERATIONS;

	@Override
	public double DOUBLE_EPSILON (final int number_of_safe_operations) {
		if (number_of_safe_operations < 1) {
			Err.reportError("number_of_safe_operations must be in[1;10000]");
		}
		if (number_of_safe_operations > 100000) {
			Err.reportError("number_of_safe_operations must be in[1;100000]");
		}
		return this.JAVA_DOUBLE_PRECISION * number_of_safe_operations;
	}

	public final int min (final int x, final int y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public final double is (final boolean in) {
		if (in) {
			return 1d;
		}
		return 0;
	}

	public final int max (final int x, final int y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	@Override
	public final double min (final double x, final double y) {
		// TODO Auto-generated method stub
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	@Override
	public final double sin (final double f) {
		return this.native_sin(f);
	}

	@Override
	public final double abs (final double x) {
		if (x < this.VAL_0) {
			return -x;
		}
		return x;
	}

	@Override
	public final double cos (final double a) {
		return this.native_cos(a);
	}

	@Override
	public final double sqrt (final double f) {
		return this.native_sqrt(f);
	}

	@Override
	public final double power (final double f, final double d) {
		return this.native_pow(f, d);
	}

	public final double PI = java.lang.Math.PI;
	public final double mPI = -java.lang.Math.PI;

	public final double ONE = 1d;
	public final double ZERO = 0d;

	public final double VAL_PI_d_180 = (java.lang.Math.PI / 180d);
	public final double VAL_180_d_PI = (180d / java.lang.Math.PI);

	public final double VAL_PI_d_2 = (java.lang.Math.PI / 2d);
	public final double VAL_mPI_d_2 = (-java.lang.Math.PI / 2d);

	public final double VAL_PI_d_4 = (java.lang.Math.PI / 4d);
	public final double VAL_mPI_d_4 = (-java.lang.Math.PI / 4d);

	public final double VAL_3PI_d_2 = (java.lang.Math.PI * 3d / 2d);;
	public final double VAL_m3PI_d_2 = (-java.lang.Math.PI * 3d / 2d);;

	public final double VAL_3PI_d_4 = (java.lang.Math.PI * 3d / 4d);;
	public final double VAL_m3PI_d_4 = (-java.lang.Math.PI * 3d / 4d);;

	public final double VAL_2PI = (java.lang.Math.PI * 2d);

	public final double VAL_0 = 0d;
	public final double VAL_1 = 1d;
	public final double VAL_k2_d_2 = (StrictMath.sqrt(2) / 2d);
	public final double VAL_mk2_d_2 = (-StrictMath.sqrt(2) / 2d);
	public final double VAL_PI_d_6 = (java.lang.Math.PI / 6d);
	public final double VAL_m2PI = -this.VAL_2PI;
	public final double VAL_PI_d_8 = (java.lang.Math.PI / 8d);

	@Override
	public final double aOmegaAB (final double a) {
		return this.native_asin(a);
	}

	public abstract double native_sin (double f);

	public abstract double native_asin (double f);

	public abstract double native_cos (double f);

	public abstract double native_pow (double f, double d);

	public abstract double native_sqrt (double f);

	@Override
	public final double toDegrees (final double r) {
		return r * this.VAL_180_d_PI;
	}

	@Override
	public final double toRadians (final double degrees) {
		return degrees * this.VAL_PI_d_180;
	}

	@Override
	public final double norm (final double x, final double y) {
		return this.native_sqrt(x * x + y * y);
	}

	public final double normAngle (final double value) {
		double tmp_ = value;

		if (tmp_ < this.VAL_0) {
			do {
				tmp_ = tmp_ + this.VAL_2PI;
			} while (tmp_ < this.VAL_0);

		} else if (tmp_ >= this.VAL_2PI) {
			do {
				tmp_ = tmp_ - this.VAL_2PI;
			} while ((tmp_ >= this.VAL_2PI));

		}

		return tmp_;
	}

	public double atan (final double f) {
		return java.lang.Math.atan(f);
	}

	@Override
	public double tan (final double angle) {
		return java.lang.Math.tan(angle);
	}

	@Override
	public final int INDEX (final boolean b) {
		if (b) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public double distance (final double x1, final double y1, final double x2, final double y2) {
		// TODO Auto-generated method stub
		return this.sqrt(this.power(x2 - x1, 2) + this.power(y2 - y1, 2));
	}

	public final double max (final double x, final double y, final double... values) {
		final int n = values.length;
		double max_xy = this.max(x, y);
		for (int i = 0; i < n; i++) {
			max_xy = this.max(max_xy, values[i]);
		}
		return max_xy;
	}

	@Override
	public final double max (final double x, final double y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public int signum (final double value) {
		if (this.isWithinEpsilon(value)) {
			return 0;
		}
		if (value > 0) {
			return 1;
		}
		return -1;
	}

	@Override
	public long floorUp (final double d) {
		final long int_d = (long)d;
		if (int_d == d) {
			return int_d;
		}
		if (d < this.ZERO) {
			return int_d;
		}
		return int_d + 1;
	}

	@Override
	public long floorDown (final double d) {
		final long int_d = (long)d;
		if (int_d == d) {
			return int_d;
		}
		if (d < this.ZERO) {
			return int_d - 1;
		}
		return int_d;
	}

	@Override
	public long integerPartOf (final double d) {
		return (int)d;
	}

	@Override
	public long round (final double float_value) {
		return (long)(float_value + this.signum(float_value) * 0.5d);
	}

	@Override
	public double fractionalPartOf (final double d) {
		double t = this.abs(d) - this.abs(this.integerPartOf(d));

		if (d < 0d) {
			t = -t;
		}
		// double missing = abs(d) - abs(integerPartOf(d)) - abs(t);
		return t;
	}

	@Override
	public boolean isInteger (final double value) {
		return this.integerPartOf(value) == value;
	}

	@Override
	public boolean isIntegerInEpsilonNeighbourhood (final double value, final double epsilon) {
		if (this.isInteger(value)) {
			return true;
		}
		if (this.abs(this.fractionalPartOf(value)) < epsilon) {
			return true;
		}
		final double abs = this.abs(value);
		if (this.abs(this.ONE - this.fractionalPartOf(abs)) < epsilon) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isIntegerInDoubleEpsilonNeighbourhood (final double value) {
		return this.isIntegerInEpsilonNeighbourhood(value, this.DOUBLE_EPSILON);
	}

	@Override
	public boolean isIntegerInFloatEpsilonNeighbourhood (final double value) {
		return this.isIntegerInEpsilonNeighbourhood(value, this.FLOAT_EPSILON);
	}

	@Override
	public boolean isFloatInteger (final double d) {
		return this.isInteger((float)d);
	}

	@Override
	public boolean isWithinEpsilon (final double small_value) {
		return this.abs(small_value) < this.EPSILON;
	}

	public boolean isWithinEpsilon (final double small_value, final double EPSILON) {
		return this.abs(small_value) < EPSILON;
	}

	@Override
	public boolean isEpsilonEqualFloat (final double double1, final double double2) {
		return this.isWithinEpsilon(this.abs(double1 - double2), this.FLOAT_EPSILON);
	}

	@Override
	public boolean isEpsilonEqualDouble (final double double1, final double double2) {
		return this.isWithinEpsilon(this.abs(double1 - double2), this.DOUBLE_EPSILON);
	}

	@Override
	public boolean isEpsilonEqual (final double double1, final double double2) {
		return this.isWithinEpsilon(this.abs(double1 - double2), this.EPSILON);
	}

	@Override
	public double ONE () {
		return this.ONE;
	}

	@Override
	public double DOUBLE_EPSILON () {
		return this.DOUBLE_EPSILON;
	}

	@Override
	public double EPSILON () {
		return this.EPSILON;
	}

	@Override
	public double FLOAT_EPSILON () {
		return this.FLOAT_EPSILON;
	}

	@Override
	public double VAL_PI_d_2 () {
		return this.VAL_PI_d_2;
	}

	@Override
	public double VAL_0 () {
		return this.VAL_0;
	}

	@Override
	public double PI () {
		return this.PI;
	}

	@Override
	public double VAL_k2_d_2 () {
		return this.VAL_k2_d_2;
	}

	@Override
	public double VAL_3PI_d_2 () {
		return this.VAL_3PI_d_2;
	}

	@Override
	public double VAL_mk2_d_2 () {
		return this.VAL_mk2_d_2;
	}

	@Override
	public double VAL_mPI_d_2 () {
		return this.VAL_mPI_d_2;
	}

	@Override
	public double VAL_2PI () {
		return this.VAL_2PI;
	}

	@Override
	public double roundToDigit (final double raw_value, final int index_after_point) {
		return this.round(raw_value * this.power(10, index_after_point)) / this.power(10, index_after_point);
	}

	@Override
	public double limit (final double left_border, final double value, final double right_border) {
		if (left_border > right_border) {
			return this.limit(right_border, value, left_border);
		}
		if (value < left_border) {
			return left_border;
		}
		if (value > right_border) {
			return right_border;
		}
		return value;

	}

	@Override
	public float limit (final float left_border, final float value, final float right_border) {
		return (float)this.limit((double)left_border, (double)value, (double)right_border);
	}

	@Override
	public Average newAverage (final int max_num_of_values) {
		return new RedAverage(max_num_of_values);
	}

}
