package com.jfixby.red.lambda;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.lambda.LambdaComponent;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.lambda.λFunctionCache;

public class RedLambda implements LambdaComponent {

	@Override
	public <X, Y> λFunction<X, Y> newFunction(λFunction<X, Y> exe) {
		return this.newFunction(exe, new MapGraph<X, Y>(JUtils.newMap()));
	}

	@Override
	public <X, Y> λFunction<X, Y> newFunction(λFunction<X, Y> exe, λFunctionCache<X, Y> graph) {
		if (graph == null) {
			graph = new EmptyGraph<X, Y>();
		}
		return new RedFunction<X, Y>(exe, graph);
	}
}
