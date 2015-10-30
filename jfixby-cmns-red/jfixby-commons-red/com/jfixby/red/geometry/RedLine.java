package com.jfixby.red.geometry;

import com.jfixby.cmns.api.geometry.Line;
import com.jfixby.cmns.api.geometry.Vertex;

public class RedLine extends VertexMaster implements Line {

	private RedVertex a;
	private RedVertex b;

	RedLine() {
		a = new RedVertex(this);
		b = new RedVertex(this);
	}

	public RedLine(Line other) {
		this();
		this.a.set(other.A());
		this.b.set(other.B());
		throw new Error();
	}

	@Override
	public Vertex A() {
		return a;
	}

	@Override
	public Vertex B() {
		return b;
	}

}
