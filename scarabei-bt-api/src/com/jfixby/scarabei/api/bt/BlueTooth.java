
package com.jfixby.scarabei.api.bt;

import com.jfixby.scarabei.api.ComponentInstaller;

public class BlueTooth {

	static private ComponentInstaller<BlueToothComponent> componentInstaller = new ComponentInstaller<BlueToothComponent>(
		"BlueTooth");

	public static final void installComponent (final BlueToothComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final BlueToothComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final BlueToothComponent component () {
		return componentInstaller.getComponent();
	}

// public static BTSpecs newBTSpecs () {
// return invoke().newBTSpecs();
// }

// public static BT newBT (final BTSpecs specs) {
// return invoke().newBT(specs);
// }

}
