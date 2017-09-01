
package com.jfixby.scarabei.api.reflect;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.names.ID;

public class Reflections {

	static private ComponentInstaller<ReflectionsComponent> componentInstaller = new ComponentInstaller<ReflectionsComponent>(
		"Reflections");

	public static final void installComponent (final ReflectionsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final ReflectionsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final ReflectionsComponent component () {
		return componentInstaller.getComponent();
	}

	public static final Class<?> resolveClass (final ID className) throws ClassNotFoundException {
		return invoke().resolveClass(className);
	}

	public static ID nameOf (final Class<?> klass) {
		return invoke().nameOf(klass);
	}

}
