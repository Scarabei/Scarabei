package com.jfixby.cmns.api.debug;

import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.sys.Sys;

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
	
}
