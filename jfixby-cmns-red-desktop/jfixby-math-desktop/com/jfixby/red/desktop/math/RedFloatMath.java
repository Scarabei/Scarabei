package com.jfixby.red.desktop.math;

import com.jfixby.cmns.api.math.FloatMathComponent;

public abstract class RedFloatMath implements FloatMathComponent {

	public final double TWICE_PI = 2d * java.lang.Math.PI;
	public final double PLUS_PI = java.lang.Math.PI;
	public final double MINUS_PI = -java.lang.Math.PI;

	public final int POSSIBLE_NUMBER_OF_SAFE_FLOAT_OPERATIONS = 10000;

	public final double JAVA_FLOAT_PRECISION = pow(2, -25);
	public final double JAVA_DOUBLE_PRECISION = pow(2, -53);

	// f1==f2 <=> |f1-f2|<FLOAT_EPSILON
	public final double FLOAT_EPSILON//
	= JAVA_FLOAT_PRECISION * POSSIBLE_NUMBER_OF_SAFE_FLOAT_OPERATIONS;

	// distinguish floats from doubles
	// f1==f2 but d1!=d2
	public final double EPSILON//
	= JAVA_FLOAT_PRECISION / 2d;

	// d1==d2 <=> |d1-d2|<DOUBLE_EPSILON
	public final double DOUBLE_EPSILON//
	= JAVA_DOUBLE_PRECISION * POSSIBLE_NUMBER_OF_SAFE_FLOAT_OPERATIONS;

	@Override
	public double DOUBLE_EPSILON(int number_of_safe_operations) {
		if (number_of_safe_operations < 1) {
			throw new Error("number_of_safe_operations must be in[1;10000]");
		}
		if (number_of_safe_operations > 100000) {
			throw new Error("number_of_safe_operations must be in[1;100000]");
		}
		return JAVA_DOUBLE_PRECISION * number_of_safe_operations;
	}

	public final int min(final int x, final int y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public final double is(final boolean in) {
		if (in) {
			return 1d;
		}
		return 0;
	}

	public final int max(final int x, final int y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public final double min(final double x, final double y) {
		// TODO Auto-generated method stub
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public final double sin(final double f) {
		return native_sin(f);
	}

	public final double abs(final double x) {
		if (x < VAL_0) {
			return -x;
		}
		return x;
	}

	public final double cos(final double a) {
		return native_cos(a);
	}

	public final double sqrt(final double f) {
		return native_sqrt(f);
	}

	public final double pow(final double f, final double d) {
		return native_pow(f, d);
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
	public final double VAL_m2PI = -VAL_2PI;
	public final double VAL_PI_d_8 = (java.lang.Math.PI / 8d);

	public final double aOmegaAB(final double a) {
		return native_asin(a);
	}

	public abstract double native_sin(double f);

	public abstract double native_asin(double f);

	public abstract double native_cos(double f);

	public abstract double native_pow(double f, double d);

	public abstract double native_sqrt(double f);

	public final double toDegrees(final double r) {
		return r * VAL_180_d_PI;
	}

	public final double toRadians(final double g) {
		return g * VAL_PI_d_180;
	}

	public final double norm(final double x, final double y) {
		return native_sqrt(x * x + y * y);
	}

	public final double normAngle(final double value) {
		double tmp_ = value;

		if (tmp_ < VAL_0) {
			do {
				tmp_ = tmp_ + VAL_2PI;
			} while (tmp_ < VAL_0);

		} else if (tmp_ >= VAL_2PI) {
			do {
				tmp_ = tmp_ - VAL_2PI;
			} while ((tmp_ >= VAL_2PI));

		}

		return tmp_;
	}

	public double atan(final double f) {
		return java.lang.Math.atan(f);
	}

	public double tan(double angle) {
		return java.lang.Math.tan(angle);
	}

	public final int INDEX(final boolean b) {
		if (b) {
			return 1;
		} else {
			return 0;
		}

	}

	public double distance(double x1, double y1, double x2, double y2) {
		// TODO Auto-generated method stub
		return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
	}

	public final double max(final double x, final double y, double... values) {
		int n = values.length;
		double max_xy = max(x, y);
		for (int i = 0; i < n; i++) {
			max_xy = max(max_xy, values[i]);
		}
		return max_xy;
	}

	public final double max(final double x, final double y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public int signum(double value) {
		if (isWithinEpsilon(value)) {
			return 0;
		}
		if (value > 0) {
			return 1;
		}
		return -1;
	}

	public long floorUp(double d) {
		final long int_d = (long) d;
		if (int_d == d) {
			return int_d;
		}
		if (d < ZERO) {
			return int_d;
		}
		return int_d + 1;
	}

	public long floorDown(double d) {
		final long int_d = (long) d;
		if (int_d == d) {
			return int_d;
		}
		if (d < ZERO) {
			return int_d - 1;
		}
		return int_d;
	}

	public long integerPartOf(double d) {
		return (int) d;
	}

	@Override
	public long round(double float_value) {
		return (long) (float_value + this.signum(float_value) * 0.5d);
	}

	public double fractionalPartOf(double d) {
		double t = abs(d) - abs(integerPartOf(d));
		if (d < 0d) {
			return -t;
		}
		return t;
	}

	public boolean isInteger(final double value) {
		return integerPartOf(value) == value;
	}

	public boolean isIntegerInDoubleEpsilonNeighbourhood(final double value) {
		if (isInteger(value)) {
			return true;
		}
		if (abs(fractionalPartOf(value)) < DOUBLE_EPSILON) {
			return true;
		}
		final double abs = abs(value);
		if (abs(ONE - fractionalPartOf(abs)) < DOUBLE_EPSILON) {
			return true;
		}
		return false;
	}

	public boolean isIntegerInFloatEpsilonNeighbourhood(final double value) {
		if (isIntegerInDoubleEpsilonNeighbourhood(value)) {
			return true;
		}
		if (abs(fractionalPartOf(value)) < FLOAT_EPSILON) {
			return true;
		}
		final double abs = abs(value);
		if (abs(ONE - fractionalPartOf(abs)) < FLOAT_EPSILON) {
			return true;
		}
		return false;
	}

	public boolean isFloatInteger(double d) {
		return isInteger((float) d);
	}

	public boolean isWithinEpsilon(final double small_value) {
		return abs(small_value) < EPSILON;
	}

	public boolean isWithinEpsilon(final double small_value,
			final double EPSILON) {
		return abs(small_value) < EPSILON;
	}

	public boolean isEpsilonEqualFloat(double double1, double double2) {
		return isWithinEpsilon(abs(double1 - double2), FLOAT_EPSILON);
	}

	public boolean isEpsilonEqualDouble(double double1, double double2) {
		return isWithinEpsilon(abs(double1 - double2), DOUBLE_EPSILON);
	}

	public boolean isEpsilonEqual(double double1, double double2) {
		return isWithinEpsilon(abs(double1 - double2), EPSILON);
	}

	@Override
	public double ONE() {
		return ONE;
	}

	@Override
	public double DOUBLE_EPSILON() {
		return DOUBLE_EPSILON;
	}

	@Override
	public double EPSILON() {
		return EPSILON;
	}

	@Override
	public double FLOAT_EPSILON() {
		return FLOAT_EPSILON;
	}

	@Override
	public double VAL_PI_d_2() {
		return VAL_PI_d_2;
	}

	@Override
	public double VAL_0() {
		return VAL_0;
	}

	@Override
	public double PI() {
		return PI;
	}

	@Override
	public double VAL_k2_d_2() {
		return VAL_k2_d_2;
	}

	@Override
	public double VAL_3PI_d_2() {
		return VAL_3PI_d_2;
	}

	@Override
	public double VAL_mk2_d_2() {
		return VAL_mk2_d_2;
	}

	@Override
	public double VAL_mPI_d_2() {
		return VAL_mPI_d_2;
	}

	@Override
	public double VAL_2PI() {
		return VAL_2PI;
	}

}
