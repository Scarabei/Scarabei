package com.jfixby.red.desktop.test;

import com.jfixby.cmns.desktop.DesktopAssembler;

public class LambdaImage {

	public interface Function2D {
		float value(int x, int y);
	}

	public interface Function2DOperator {
		Function2D apply(Function2D f);
	}

	public interface Operation {
		Function2D apply(Function2D a, Function2D b);
	}

	public static void main(String[] args) {
		DesktopAssembler.setup();

		Function2D image = (x, y) -> 0;

		Function2DOperator dFx = f -> ((x, y) -> (f.value(x + 1, y) - f.value(x - 1, y)) / 2f);
		Function2DOperator dFy = f -> ((x, y) -> (f.value(x, y + 1) - f.value(x, y - 1)) / 2f);

		Function2DOperator GradXX = f -> dFx.apply(dFx.apply(f));
		Function2DOperator GradXY = f -> dFx.apply(dFy.apply(f));
		Function2DOperator GradYX = f -> dFy.apply(dFx.apply(f));
		Function2DOperator GradYY = f -> dFy.apply(dFy.apply(f));

		Operation multiply = (f, g) -> ((x, y) -> (f.value(x, y) * g.value(x, y)));
		Operation substract = (f, g) -> ((x, y) -> (f.value(x, y) - g.value(x,y)));

		Function2DOperator GardXXYY = f -> multiply.apply(GradXX.apply(f), GradYY.apply(f));
		Function2DOperator GradXYYX = f -> multiply.apply(GradXY.apply(f), GradYX.apply(f));
		Function2DOperator normGrad = f -> substract.apply(GardXXYY.apply(f), GradXYYX.apply(f));

		Function2D sketchy = normGrad.apply(image);

	}

	private static float abs(float value) {
		return Math.abs(value);
	}
}
