package com.jfixby.red.lambda;

import com.jfixby.cmns.api.lambda.λFunctionCache;
import com.jfixby.cmns.api.log.L;

public class EmptyGraph<X, Y> implements λFunctionCache<X, Y> {

	@Override
	final public void print(String tag) {
		L.d("EmptyGraph");
	}

	@Override
	public Y get(X value_number) {
		return null;
	}

	@Override
	public void put(X value_number, Y value) {
	}
}
