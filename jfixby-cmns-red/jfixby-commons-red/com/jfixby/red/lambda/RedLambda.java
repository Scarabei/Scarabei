package com.jfixby.red.lambda;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.lambda.LambdaComponent;
import com.jfixby.cmns.api.lambda.λExpression;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.lambda.λGraph;

public class RedLambda implements LambdaComponent {

	@Override
	public <X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe) {
		return this.newFunction(exe, new EmptyGraph<X, Y>());
	}

	@Override
	public <X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe, Map<X, Y> map) {
		return this.newFunction(exe, new MapGraph<X, Y>(map));
	}

	@Override
	public <X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe, λGraph<X, Y> graph) {
		return new RedFunction<X, Y>(exe, graph);
	}

}
