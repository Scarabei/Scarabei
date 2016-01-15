package com.jfixby.red.lambda;

import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.lambda.λFunctionCache;

public class RedFunction<X, Y> implements λFunction<X, Y> {
	final private λFunction<X, Y> exe;
	final private λFunctionCache<X, Y> graph;
	private Y value;

	public RedFunction(λFunction<X, Y> exe, λFunctionCache<X, Y> graph) {
		this.exe = exe;
		this.graph = graph;
	}

	@Override
	public Y val(X input) {
		this.value = this.graph.get(input);
		if (this.value == null) {
			this.value = this.exe.val(input);
			this.graph.put(input, this.value);
			// this.graph.print("put: " + input);
		}
		// L.d(input);
		return this.value;
	}
}
