
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.geometry.Line;
import com.jfixby.scarabei.api.geometry.Vertex;

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
		Err.throwNotImplementedYet();
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
