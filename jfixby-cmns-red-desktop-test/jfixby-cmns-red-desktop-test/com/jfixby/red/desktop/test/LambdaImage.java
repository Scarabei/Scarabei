package com.jfixby.red.desktop.test;

import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.red.desktop.test.ExampleLambda.FunctionalInterface;

public class LambdaImage {

	public interface Matrix {
		float value(int x, int y);
	}

	public interface Function {
		float value(int x, int y);
	}

	public interface PointOperator {
		float value(Function f, int x0, int y0);
	}

	public interface Norm {
		float value(VectorN n);
	}

	public interface VectorN {
		float value(int n);
	}

	public interface Operator {
		Function value(Function f);
	}

	static Norm factorial = null;

	public static void main(String[] args) {
		DesktopAssembler.setup();

		Function image = (x, y) -> x + y;

		PointOperator dFx = (f, x0, y) -> (f.value(x0 + 1, y) - f.value(x0 - 1,
				y)) / 2f;
		PointOperator dFy = (f, x, y0) -> (f.value(x, y0 + 1) - f.value(x,
				y0 - 1)) / 2f;

		Operator normGradF = f -> ((x, y) -> (abs(dFx.value(f, x, y)) + abs(dFy.value(f, x, y))));
		// PointOperator grad = (f, x0, y0) -> 0;
		// Function frad = (x0, y0) -> x + y;

		// Operator diffX = f(x,y) -> f(x + 1, y) - f(x - 1, y);

	}

	private static float abs(float value) {
		return Math.abs(value);
	}
}
