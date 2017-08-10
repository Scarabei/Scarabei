
package com.jfixby.scarabei.api.ui;

import com.jfixby.scarabei.api.ComponentInstaller;

public class UIThread {

	static private ComponentInstaller<UIThreadComponent> componentInstaller = new ComponentInstaller<UIThreadComponent>(
		"UIThread");

	public static final void installComponent (final UIThreadComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final UIThreadComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final UIThreadComponent component () {
		return componentInstaller.getComponent();
	}

	public static void registerUIThread () {
		invoke().registerUIThread();
	}

	public static boolean isUIThread () {
		return invoke().isUIThread();
	}

}
