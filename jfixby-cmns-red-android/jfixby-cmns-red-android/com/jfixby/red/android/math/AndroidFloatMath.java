package com.jfixby.red.android.math;

import com.jfixby.red.math.RedFloatMath;

public class AndroidFloatMath extends RedFloatMath {

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

	@Override
	public double log(double base, double exp_value) {
		return StrictMath.log(exp_value) / StrictMath.log(base);
	}

}
