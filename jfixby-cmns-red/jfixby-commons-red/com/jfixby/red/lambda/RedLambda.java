package com.jfixby.red.lambda;

import com.jfixby.cmns.api.lambda.λExpression;
import com.jfixby.cmns.api.lambda.LambdaComponent;
import com.jfixby.cmns.api.lambda.λFunction;

public class RedLambda implements LambdaComponent {

	@Override
	public <X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe) {
		return new RedFunction<X, Y>(exe);
	}

}
