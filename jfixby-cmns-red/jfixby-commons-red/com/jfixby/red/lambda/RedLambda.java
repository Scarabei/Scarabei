package com.jfixby.red.lambda;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.lambda.LambdaComponent;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.lambda.λFunctionCache;

public class RedLambda implements LambdaComponent {

	@Override
	public <X, Y> λFunction<X, Y> newFunction(λFunction<X, Y> exe) {
		return this.newFunction(exe, new MapGraph<X, Y>());
	}

	@Override
	public <X, Y> λFunction<X, Y> newFunction(λFunction<X, Y> exe, λFunctionCache<X, Y> graph) {
		if (graph == null) {
			graph = new EmptyGraph<X, Y>();
		}
		return new RedFunction<X, Y>(exe, graph);
	}

	@Override
	final public <X, Y, Z> λFunction<X, Z> compose(final λFunction<Y, Z> g, final λFunction<X, Y> f) {
		λFunction<X, Z> result = new λFunction<X, Z>() {
			@Override
			public Z val(X input) {
				return g.val(f.val(input));
			}
		};
		return result;
	}

	@Override
	public <X, Y> λFunctionCache<X, Y> newDebugCache() {
		return new DebugGraph<X, Y>();
	}

	@Override
	public <Q, T> λFunctionCache<Collection<Q>, T> newArrayCache() {
		return new ArrayCache<Q, T>();
	}

}
