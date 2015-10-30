package com.jfixby.red.desktop.math;

public class DesktopFloatMath extends RedFloatMath {

	@Override
	public double native_sin(double f) {
		return StrictMath.sin(f);
	}

	@Override
	public double native_asin(double f) {
		return StrictMath.asin(f);
	}

	@Override
	public double native_cos(double f) {
		return StrictMath.cos(f);
	}

	@Override
	public double native_pow(double f, double d) {
		return StrictMath.pow(f, d);
	}

	@Override
	public double native_sqrt(double f) {
		return StrictMath.sqrt(f);
	}

	public double limit(double left_border, double value, double right_border) {
		if (left_border > right_border) {
			return limit(right_border, value, left_border);
		}
		if (value < left_border) {
			return left_border;
		}
		if (value > right_border) {
			return right_border;
		}
		return value;

	}

	

	

}
