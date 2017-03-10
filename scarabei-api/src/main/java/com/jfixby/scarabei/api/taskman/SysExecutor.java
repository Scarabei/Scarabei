
package com.jfixby.scarabei.api.taskman;

import com.jfixby.scarabei.api.ComponentInstaller;

public class SysExecutor {

	static private ComponentInstaller<ExecutorComponent> componentInstaller = new ComponentInstaller<ExecutorComponent>(
		"SysExecutor");

	public static final void installComponent (ExecutorComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final ExecutorComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final ExecutorComponent component () {
		return componentInstaller.getComponent();
	}

	public static void onSystemStart () {
		invoke().onSystemStart();
	}

	public static void pushTasks () {
		invoke().pushTasks();
	}

}
