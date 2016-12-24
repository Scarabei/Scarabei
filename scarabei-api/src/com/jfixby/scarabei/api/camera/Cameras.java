
package com.jfixby.scarabei.api.camera;

import com.jfixby.scarabei.api.ComponentInstaller;

public class Cameras {
	static private ComponentInstaller<CamerasComponent> componentInstaller = new ComponentInstaller<CamerasComponent>("Camera");

	public static final void installComponent (final CamerasComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final CamerasComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final CamerasComponent component () {
		return componentInstaller.getComponent();
	}

	public static void register (final Camera camera) {
		invoke().register(camera);
	}

}
