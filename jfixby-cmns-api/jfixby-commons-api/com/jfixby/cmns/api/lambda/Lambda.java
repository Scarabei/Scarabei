package com.jfixby.cmns.api.lambda;

import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.components.ComponentInstaller;

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

	public static <X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe) {
		return invoke().newFunction(exe);
	}

	public static <X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe, Map<X, Y> java_map) {
		return invoke().newFunction(exe, java_map);
	}

	public static <X, Y> λFunction<X, Y> newFunction(λExpression<X, Y> exe, λGraph<X, Y> graph) {
		return invoke().newFunction(exe, graph);
	}

}
