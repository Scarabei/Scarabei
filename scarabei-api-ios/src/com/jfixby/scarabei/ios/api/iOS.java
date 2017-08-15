
package com.jfixby.scarabei.ios.api;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.display.DisplayMetrics;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.ios.api.camera.iOSCameraSetup;

public class iOS {

	static private ComponentInstaller<iOSComponent> componentInstaller = new ComponentInstaller<>("iOS");

	public static final void installComponent (final iOSComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final iOSComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final iOSComponent component () {
		return componentInstaller.getComponent();
	}

	public static iOSCameraSetup getCameraSetup () {
		return invoke().getCameraSetup();

	}

	public static DisplayMetrics getDisplayMetrics () {
		return invoke().getDisplayMetrics();
	}

	public static iOSAppVersion getAppVersion () {
		return invoke().getAppVersion();
	}

	public static Map<ID, Object> getSystemInfo () {
		return invoke().getSystemInfo();
	}

	public static long getMaxHeapSize () {
		return invoke().getMaxHeapSize();
	}

	public static long getRecommendedHeapSize () {
		return invoke().getRecommendedHeapSize();
	}

}
