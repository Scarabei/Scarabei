package com.jfixby.cmns.api.arrays;

import com.jfixby.cmns.api.ComponentInstaller;

public class Arrays {

	static private ComponentInstaller<ArraysComponent> componentInstaller = new ComponentInstaller<ArraysComponent>("Arrays");

	public static final void installComponent(ArraysComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final ArraysComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final ArraysComponent component() {
		return componentInstaller.getComponent();
	}

}
