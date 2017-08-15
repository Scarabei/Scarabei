
package com.jfixby.scarabei.api.mobile;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.names.ID;

public class MobileDevice {

	static private ComponentInstaller<MobileDeviceComponent> componentInstaller = new ComponentInstaller<MobileDeviceComponent>(
		"MobileDevice");

	public static final void installComponent (final MobileDeviceComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final MobileDeviceComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final MobileDeviceComponent component () {
		return componentInstaller.getComponent();
	}

	public static long getMaxHeapSize () {
		return invoke().getMaxHeapSize();
	}

	public static long getRecommendedHeapSize () {
		return invoke().getRecommendedHeapSize();
	}

	public static String getApplicationPrivateDirPathString () {
		return invoke().getApplicationPrivateDirPathString();
	}

	public static File getPrivateFolder () {
		return invoke().getPrivateFolder();
	}

	public static File getCacheFolder () {
		return invoke().getCacheFolder();
	}

	public static String getBrand () {
		return invoke().getBrand();
	}

	public static String getModel () {
		return invoke().getModel();
	}

	public static String getHost () {
		return invoke().getHost();
	}

	public static String getVersionRelease () {
		return invoke().getVersionRelease();
	}

	public static Map<ID, Object> getSystemInfo () {
		return invoke().getSystemInfo();
	}

}
