package com.jfixby.cmns.api.random;

import com.jfixby.cmns.api.ComponentInstaller;

public class Random {

	static private ComponentInstaller<RandomComponent> componentInstaller = new ComponentInstaller<RandomComponent>("Random");

	public static final void installComponent(RandomComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final RandomComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final RandomComponent component() {
		return componentInstaller.getComponent();
	}

}
