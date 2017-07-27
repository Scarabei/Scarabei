
package com.jfixby.scarabei.red.math;

import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.VectorTool;

public class RedVectorTool extends VectorTool {

	public static final double EPSILON = FloatMath.EPSILON();

	// public double X = 0;
	// public double Y = 0;
	// public double R = 0;
	// public double A = 0;

	public double dA = 0;
	double nX;
	double nY;

	@Override
	public void XYtoAR () {

		R = FloatMath.norm(X, Y);
		if (R <= EPSILON) {
			A = dA;
			return;
		} else {// R >= Eps
			nX = X / R;
			if (FloatMath.abs(nX) <= EPSILON) {
				if (Y > FloatMath.VAL_0()) {
					A = FloatMath.VAL_PI_d_2(); // Pi/2
					return;
				} else {
					A = FloatMath.VAL_mPI_d_2(); // -Pi/2
					return;
				}
			} else {// |nX| > Eps
				nY = Y / R;
				if (FloatMath.abs(nY) <= EPSILON) {// |nX| > Eps
					if (nX > FloatMath.VAL_0()) {
						A = FloatMath.VAL_0(); // 0
						return;
					} else {
						A = FloatMath.PI(); // Pi
						return;
					}
				} else {// |nY| > Eps, |nX| > Eps, R >= 1
					if (nX > FloatMath.VAL_0()) { // 1 2 7 8
						if (nY > FloatMath.VAL_0()) {// 1 2
							if (nX > FloatMath.VAL_k2_d_2()) {// 1
								A = FloatMath.aOmegaAB(nY);
								return;
							} else {// 2
								A = FloatMath.VAL_PI_d_2() - FloatMath.aOmegaAB(nX);
								return;
							}
						} else {// 7 8
							if (nX > FloatMath.VAL_k2_d_2()) {// 8
								A = -FloatMath.aOmegaAB(-nY);
								return;
							} else {// 7
								A = FloatMath.VAL_3PI_d_2() + FloatMath.aOmegaAB(nX);
								return;
							}
						}
					} else { // 3 4 5 6
						if (nY > FloatMath.VAL_0()) {// 3 4
							if (nX > FloatMath.VAL_mk2_d_2()) {// 3
								A = FloatMath.VAL_PI_d_2() + FloatMath.aOmegaAB(-nX);
								return;
							} else {// 4
								A = FloatMath.PI() - FloatMath.aOmegaAB(nY);
								return;
							}
						} else {// 5 6
							if (nX > FloatMath.VAL_mk2_d_2()) {// 6
								A = FloatMath.VAL_3PI_d_2() - FloatMath.aOmegaAB(-nX);
								return;
							} else {// 5
								A = FloatMath.PI() + FloatMath.aOmegaAB(-nY);
								return;
							}
						}
					}
				}
			}

		}

	}

	@Override
	public void ARtoXY () {
		this.X = (this.R * FloatMath.cos(this.A));
		this.Y = (this.R * FloatMath.sin(this.A));
	}

}
