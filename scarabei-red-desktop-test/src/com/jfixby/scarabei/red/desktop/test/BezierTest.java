
package com.jfixby.scarabei.red.desktop.test;

import org.junit.Test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.geometry.Bezier;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.log.L;

public class BezierTest {

	@Test
	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();

		final CanvasPosition start = Geometry.newCanvasPosition();
		start.setXY(1, 2);

		final Bezier<CanvasPosition> pStart = t -> start;

		final CanvasPosition end = Geometry.newCanvasPosition();
		end.setXY(3, 4);

		final Bezier<CanvasPosition> pEnd = t -> end;

		final Bezier<CanvasPosition> combo = Geometry.combine(pStart, pEnd);

		final double delta = 0.1;
		for (double t = 0; t < 1d; t = t + delta) {
			final CanvasPosition result = combo.valueAt(t);
			L.d("result", result);
		}

// final BezierPoint<FixedFloat2> bPoint = Geometry.newBezierPoint(start);

// final FixedFloat2 fVal = bPoint.valueAt(0.5);

	}

}
