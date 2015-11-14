package com.jfixby.red.lambda;

import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.lambda.λFunctionCache;

public class MapGraph<X, Y> implements λFunctionCache<X, Y> {

	final private Map<X, Y> map;

	public MapGraph(Map<X, Y> map) {
		this.map = map;
	}

	@Override
	public Y get(X input) {
		return this.map.get(input);
	}

	@Override
	public void put(X input, Y value) {
		this.map.put(input, value);
	}

	@Override
	public void print(String tag) {
		this.map.print(tag);
	}
}
