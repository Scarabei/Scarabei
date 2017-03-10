
package com.jfixby.scarabei.api.net;

import com.jfixby.scarabei.api.ComponentInstaller;

public class WSClient {

	static private ComponentInstaller<WSClientComponent> componentInstaller = new ComponentInstaller<WSClientComponent>(
		"WebSocketClient");

	public static final void installComponent (WSClientComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final WSClientComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final WSClientComponent component () {
		return componentInstaller.getComponent();
	}

}
