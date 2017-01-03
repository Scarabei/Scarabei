
package com.jfixby.scarabei.ios.api;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.sys.SystemInfo;
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

	public static long getMaxHeapSize () {
		return invoke().getMaxHeapSize();
	}

	public static long getRecommendedHeapSize () {
		return invoke().getRecommendedHeapSize();
	}

	public static String getApplicationPrivateDirPathString () {
		return invoke().getApplicationPrivateDirPathString();
	}

	public static iOSCameraSetup getCameraSetup () {
		return invoke().getCameraSetup();

	}

	public static File getPrivateFolder () {
		return invoke().getPrivateFolder();
	}

	public static File getCacheFolder () {
		return invoke().getCacheFolder();
	}

	public static DisplayMetrics getDisplayMetrics () {
		return invoke().getDisplayMetrics();
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

	public static iOSAppVersion getAppVersion () {
		return invoke().getAppVersion();
	}

	public static SystemInfo getSystemInfo () {
		return invoke().getSystemInfo();
	}

}
