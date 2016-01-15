package com.jfixby.cmns.api.debug;

import com.jfixby.cmns.api.ComponentInstaller;

public class Debug {

	static private ComponentInstaller<DebugComponent> componentInstaller = new ComponentInstaller<DebugComponent>("Debug");

	public static final void installComponent(DebugComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final DebugComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final DebugComponent component() {
		return componentInstaller.getComponent();
	}

	public static void printCallStack(final boolean condition) {
		invoke().printCallStack(condition);
	}

	public static void exit(final boolean condition) {
		invoke().exit(condition);
	}

	public static void printCallStack() {
		invoke().printCallStack();
	}

	public static <T> T checkNull(String parameter_name, T value) {
		return invoke().checkNull(parameter_name, value);
	}

	public static <T> T checkNull(T value) {
		return invoke().checkNull(value);
	}

	public static void checkEmpty(String parameter_name, String value) {
		invoke().checkEmpty(parameter_name, value);
	}
	
	public static void checkTrue(boolean flag) {
		invoke().checkTrue(flag);
	}

	public static void checkTrue(String flag_name, boolean flag) {
		invoke().checkTrue(flag_name, flag);
	}
	
	
	
}
