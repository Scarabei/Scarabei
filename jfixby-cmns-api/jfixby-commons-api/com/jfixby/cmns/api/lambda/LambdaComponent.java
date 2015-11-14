package com.jfixby.cmns.api.lambda;

public interface LambdaComponent {

	<X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe);

}
