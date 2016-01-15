package com.jfixby.cmns.api.err;

import com.jfixby.cmns.api.ComponentInstaller;

public class Err {

	private static final ErrorComponent DEFAULT = new DefaultErrorComponent();
	static private ComponentInstaller<ErrorComponent> componentInstaller = new ComponentInstaller<ErrorComponent>("Error");

	public static final void installComponent(ErrorComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final ErrorComponent invoke() {
		if (componentInstaller.getComponent() == null) {
			return DEFAULT;
		}
		return componentInstaller.invokeComponent();
	}

	public static final ErrorComponent component() {
		return componentInstaller.getComponent();
	}

	public static void reportWarning(String message) {
		invoke().reportWarning(message);
	}

	public static void reportError(String message) {
		invoke().reportError(message);
	}

	public static void reportError(Throwable e) {
		invoke().reportError(e);
	}

}
