
package com.jfixby.cmns.api.desktop;

import com.jfixby.cmns.api.ComponentInstaller;

public class Desktop {

	static private ComponentInstaller<DesktopComponent> componentInstaller = new ComponentInstaller<>("Desktop");

	public static final void installComponent (final DesktopComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final DesktopComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final DesktopComponent component () {
		return componentInstaller.getComponent();
	}

}
