package com.jfixby.cmns.api.lambda;

import com.jfixby.cmns.api.collections.Map;

public interface LambdaComponent {

	<X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe);

	<X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe, Map<X, Y> graph);

	<X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe, λGraph<X, Y> graph);

}
