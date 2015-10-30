package com.jfixby.red.geometry;

import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.Triangle;
import com.jfixby.cmns.api.math.MathTools;

public class RedTriangle extends VertexMaster implements Triangle {

	private RedVertex A;
	private RedVertex B;
	private RedVertex C;
	private Float2 tmp;

	public RedTriangle() {
		super();
		A = new RedVertex(this);
		B = new RedVertex(this);
		C = new RedVertex(this);
		tmp = Geometry.newFloat2();

	}

	@Override
	public RedVertex A() {
		return A;
	}

	@Override
	public RedVertex B() {
		return B;
	}

	@Override
	public RedVertex C() {
		return C;
	}

	@Override
	public boolean containsPoint(double world_x, double world_y) {
		tmp.setXY(world_x, world_y);
		return this.containsPoint(tmp);
	}

	@Override
	public boolean containsPoint(FixedFloat2 point) {
		if (this.A.transformed().isInEpsilonDistance(point)) {
			return true;
		}
		if (this.B.transformed().isInEpsilonDistance(point)) {
			return true;
		}
		if (this.C.transformed().isInEpsilonDistance(point)) {
			return true;
		}

		return (MathTools.pointLiesInsideTriangle(tmp.getX(), tmp.getY(), A
				.transformed().getX(), A.transformed().getY(),
				B.transformed().getX(), B.transformed().getY(),
				C.transformed().getX(), C.transformed().getY()));
	}
}
