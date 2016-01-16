package com.jfixby.cmns.api.lambda;

import com.jfixby.cmns.api.collections.Collection;

public interface LambdaComponent {

	public <X, Y> λFunction<X, Y> newFunction(λFunction<X, Y> exe);

	public <X, Y> λFunction<X, Y> newFunction(λFunction<X, Y> exe, λFunctionCache<X, Y> graph);

	public <X, Y, Z> λFunction<X, Z> compose(λFunction<Y, Z> g, λFunction<X, Y> f);

	public <X, Y> λFunctionCache<X, Y> newDebugCache();

	public <Q, T> λFunctionCache<Collection<Q>, T> newArrayCache();

}
