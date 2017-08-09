
package com.jfixby.scarabei.api.debug;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.Collection;

public class Debug {

	static private ComponentInstaller<DebugComponent> componentInstaller = new ComponentInstaller<DebugComponent>("Debug");

	public static final void installComponent (final DebugComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final DebugComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final DebugComponent component () {
		return componentInstaller.getComponent();
	}

	public static Collection<StackTraceElement> getStackTrace () {
		return invoke().getStackTrace();
	}

	public static void exit (final boolean condition) {
		invoke().exit(condition);
	}

	public static <T> T checkNull (final String parameter_name, final T value) {
		return invoke().checkNull(parameter_name, value);
	}

	public static <T> T checkNull (final T value) {
		return invoke().checkNull(value);
	}

	public static String checkEmpty (final String parameter_name, final String value) {
		return invoke().checkEmpty(parameter_name, value);
	}

	public static void checkTrue (final boolean flag) {
		invoke().checkTrue(flag);
	}

	public static void checkTrue (final String flag_name, final boolean flag) {
		invoke().checkTrue(flag_name, flag);
	}

	public static DebugTimer newTimer () {
		return invoke().newTimer();
	}

	public static DebugTimer newTimer (final DEBUG_TIMER_MODE mode) {
		return invoke().newTimer(mode);
	}

	public static void reportWarning (final String msg) {
		invoke().reportWarning(msg);
	}

	public static void checkCurrentThreadIsMain () {
		invoke().checkCurrentThreadIsMain();
	}

}
