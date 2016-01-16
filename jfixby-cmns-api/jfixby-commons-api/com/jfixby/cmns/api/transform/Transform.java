package com.jfixby.cmns.api.transform;

import com.jfixby.cmns.api.ComponentInstaller;

public class Transform {

	static private ComponentInstaller<TransformComponent> componentInstaller = new ComponentInstaller<TransformComponent>(
			"Transform");

	public static final void installComponent(
			TransformComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final TransformComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final TransformComponent component() {
		return componentInstaller.getComponent();
	}
}
