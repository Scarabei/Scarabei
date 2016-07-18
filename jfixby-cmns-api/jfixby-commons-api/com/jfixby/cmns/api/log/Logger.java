
package com.jfixby.cmns.api.log;

import com.jfixby.cmns.api.ComponentInstaller;

public class Logger {

	static private ComponentInstaller<LoggerComponent> componentInstaller = new ComponentInstaller<LoggerComponent>("Logger");

	public static final void installComponent (final LoggerComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final LoggerComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final LoggerComponent component () {
		return componentInstaller.getComponent();
	}

	public static void d (final Object msg) {
		invoke().d(msg);
	}

	public static void d (final Object tag, final Object msg) {
		invoke().d(tag, msg);
	}

	public static void d (final Object... msg) {
		invoke().d(msg);
	}

	public static void e (final Object msg) {
		invoke().e(msg);
	}

	public static void e (final Object tag, final Object msg) {
		invoke().e(tag, msg);
	}

	public static void d () {
		invoke().d();
	}

	public static void e () {
		invoke().e();
	}

	public static void d_addChars (final Object msg) {
		invoke().d_addChars(msg);
	}

	public static String toString (final Object[] doubles) {
		return invoke().toString(doubles);
	}

	public static void deInstallCurrentComponent () {
		componentInstaller.deInstallCurrentComponent();
	}

}
