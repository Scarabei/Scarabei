package com.jfixby.red.lambda;

import com.jfixby.cmns.api.lambda.λGraph;
import com.jfixby.cmns.api.log.L;

public class EmptyGraph<X, Y> implements λGraph<X, Y> {

	public EmptyGraph() {

	}

	@Override
	final public Y get(X input) {
		return null;
	}

	@Override
	final public void put(X input, Y value) {

	}

	@Override
	final public void print(String tag) {
		L.d("EmptyGraph");
	}
}
