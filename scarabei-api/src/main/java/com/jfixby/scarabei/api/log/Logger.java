
package com.jfixby.scarabei.api.log;

import com.jfixby.scarabei.api.ComponentInstaller;

public class Logger {

	static private ComponentInstaller<LoggerComponent> componentInstaller = new ComponentInstaller<LoggerComponent>("Logger");

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final void installComponent (final LoggerComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static LoggerComponent deInstallCurrentComponent () {
		return componentInstaller.deInstallCurrentComponent();
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

	public static void d (final Object tag, final int[] msg) {
		invoke().d(tag, msg);
	}

	public static void d (final Object... msg) {
		invoke().d(msg);
	}

	public static void e (final Object msg) {
		invoke().e(msg);
	}

	public static void e (final Throwable msg) {
		invoke().e(msg);
	}

	public static void e (final Object tag, final Object msg) {
		invoke().e(tag, msg);
	}

	public static void e (final Object tag, final Throwable err) {
		invoke().e(tag, err);
	}

	public static void d () {
		invoke().d();
	}

	public static void e () {
		invoke().e();
	}

	public static void d_appendChars (final Object msg) {
		invoke().d_appendChars(msg);
	}

	public static String toString (final Object[] doubles) {
		return invoke().toString(doubles);
	}

	public static String stackTraceToString (final Throwable e) {
		return invoke().stackTraceToString(e);
	}

}
