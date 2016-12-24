
package com.jfixby.scarabei.red.desktop.math;

import com.jfixby.scarabei.red.math.RedFloatMath;

public class DesktopFloatMath extends RedFloatMath {

	@Override
	public double native_sin (final double f) {
		return StrictMath.sin(f);
	}

	@Override
	public double native_asin (final double f) {
		return StrictMath.asin(f);
	}

	@Override
	public double native_cos (final double f) {
		return StrictMath.cos(f);
	}

	@Override
	public double native_pow (final double f, final double d) {
		return StrictMath.pow(f, d);
	}

	@Override
	public double native_sqrt (final double f) {
		return StrictMath.sqrt(f);
	}

	@Override
	public double log (final double base, final double exp_value) {
		return StrictMath.log(exp_value) / StrictMath.log(base);
	}

}
