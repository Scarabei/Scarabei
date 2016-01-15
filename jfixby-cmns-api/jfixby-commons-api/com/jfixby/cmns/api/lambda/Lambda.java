package com.jfixby.cmns.api.lambda;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.collections.Collection;

public class Lambda {
	static private ComponentInstaller<LambdaComponent> componentInstaller = new ComponentInstaller<LambdaComponent>("Lambda");

	public static final void installComponent(LambdaComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final LambdaComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final LambdaComponent component() {
		return componentInstaller.getComponent();
	}

	public static <X, Y> λFunction<X, Y> cache(λFunction<X, Y> exe) {
		return invoke().newFunction(exe);
	}

	public static <X, Y> λFunction<X, Y> cache(λFunction<X, Y> exe, λFunctionCache<X, Y> graph) {
		return invoke().newFunction(exe, graph);
	}

	public static <X, Y, Z> λFunction<X, Z> compose(λFunction<Y, Z> g, λFunction<X, Y> f) {
		return invoke().compose(g, f);
	}

	public static <X, Y> λFunctionCache<X, Y> newDebugCache() {
		return invoke().newDebugCache();
	}

	public static <Q, T> λFunctionCache<Collection<Q>, Collection<T>> newArrayCache() {
		return invoke().newArrayCache();
	}

}
