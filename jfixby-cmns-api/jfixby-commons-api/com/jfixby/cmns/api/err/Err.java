
package com.jfixby.cmns.api.err;

import com.jfixby.cmns.api.ComponentInstaller;

public class Err {

	static private ComponentInstaller<ErrorComponent> componentInstaller = new ComponentInstaller<ErrorComponent>("Error");

	public static final void installComponent (final ErrorComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static ErrorComponent deInstallCurrentComponent () {
		return componentInstaller.deInstallCurrentComponent();
	}

	public static final ErrorComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final ErrorComponent component () {
		return componentInstaller.getComponent();
	}

	public static void reportWarning (final String message) {
		invoke().reportWarning(message);
	}

	public static void reportError (final String message) {
		invoke().reportError(message);
	}

	public static void reportError (final Throwable e) {
		invoke().reportError(e);
	}

	public static void reportError (final String message, final Throwable e) {
		invoke().reportError(message, e);
	}

	public static void reportNotImplementedYet () {
		invoke().reportNotImplementedYet();
	}

	public static void reportGCLeak (final String msg, final Object leakingObject) {
		invoke().reportGCLeak(msg, leakingObject);
	}

	public static void reportError (final Thread t, final Throwable e) {
		invoke().reportError(t, e);
	}

}
