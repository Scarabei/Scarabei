package com.jfixby.cmns.api.transform;

import com.jfixby.cmns.api.ComponentInstaller;

public class Transform2D {

	static private ComponentInstaller<Transform2DComponent> componentInstaller = new ComponentInstaller<Transform2DComponent>(
			"Transform");

	public static final void installComponent(
			Transform2DComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final Transform2DComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final Transform2DComponent component() {
		return componentInstaller.getComponent();
	}
}
