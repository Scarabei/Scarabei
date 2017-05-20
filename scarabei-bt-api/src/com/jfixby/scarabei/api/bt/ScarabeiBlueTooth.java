
package com.jfixby.scarabei.api.bt;

import com.jfixby.scarabei.api.ComponentInstaller;

public class ScarabeiBlueTooth {

	static private ComponentInstaller<ScarabeiBlueToothComponent> componentInstaller = new ComponentInstaller<>("ScarabeiBlueTooth");

	public static final void installComponent (final ScarabeiBlueToothComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final ScarabeiBlueToothComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final ScarabeiBlueToothComponent component () {
		return componentInstaller.getComponent();
	}

}
