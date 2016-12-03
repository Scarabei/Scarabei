
package com.jfixby.red.geometry;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.geometry.Line;
import com.jfixby.cmns.api.geometry.Vertex;

public class RedLine extends VertexMaster implements Line {

	private final RedVertex a;
	private final RedVertex b;

	RedLine () {
		this.a = new RedVertex(this);
		this.b = new RedVertex(this);
	}

	public RedLine (final Line other) {
		this();
		this.a.set(other.A());
		this.b.set(other.B());
		Err.reportNotImplementedYet();
	}

	@Override
	public Vertex A () {
		return this.a;
	}

	@Override
	public Vertex B () {
		return this.b;
	}

}
