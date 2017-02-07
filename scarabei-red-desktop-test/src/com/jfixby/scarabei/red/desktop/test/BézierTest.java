
package com.jfixby.scarabei.red.desktop.test;

import org.junit.Test;

import com.jfixby.scarabei.api.desktop.DesktopSetup;
import com.jfixby.scarabei.api.floatn.FixedFloat2;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.BezierPoint;
import com.jfixby.scarabei.api.geometry.Geometry;

public class BézierTest {

	@Test
	public static void main (final String[] args) {
		DesktopSetup.deploy();

		final Float2 start = Geometry.newFloat2(1, 2);

		final BezierPoint<FixedFloat2> bPoint = Geometry.newBezierPoint(start);

		final FixedFloat2 fVal = bPoint.valueAt(0.5);

	}

}
