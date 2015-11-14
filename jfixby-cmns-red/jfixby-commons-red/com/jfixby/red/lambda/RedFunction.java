package com.jfixby.red.lambda;

import com.jfixby.cmns.api.lambda.λExpression;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.lambda.λGraph;
import com.jfixby.cmns.api.log.L;

public class RedFunction<X, Y> implements λFunction<X, Y> {
	final private λExpression<X, Y> exe;
	final private λGraph<X, Y> graph;
	private Y value;

	public RedFunction(λExpression<X, Y> exe, λGraph<X, Y> graph) {
		this.exe = exe;
		this.graph = graph;
	}

	@Override
	public Y val(X input) {
		this.value = this.graph.get(input);
		if (this.value == null) {
			this.value = this.exe.calculate(input);
			this.graph.put(input, this.value);
			// this.graph.print("put: " + input);
		}
		// L.d(input);
		return this.value;
	}
}
