
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.LinearCombinator;

public class RedLinearCanvasPositionCombinator implements LinearCombinator<CanvasPosition> {

	final private CanvasPosition a;
	final private CanvasPosition b;
	final private CanvasPosition result;

	RedLinearCanvasPositionCombinator () {
		this.a = Geometry.newCanvasPosition();
		this.b = Geometry.newCanvasPosition();
		this.result = Geometry.newCanvasPosition();
	}

	@Override
	public void setA (final CanvasPosition a) {
		this.a.setPosition(a);
	}

	@Override
	public void setB (final CanvasPosition b) {
		this.b.setPosition(b);
	}

	@Override
	public CanvasPosition getLinearCombination (final double alpha, final double betta) {
		this.result.setLinearSum(this.a, alpha, this.b, betta);
		return this.result;
	}

}
