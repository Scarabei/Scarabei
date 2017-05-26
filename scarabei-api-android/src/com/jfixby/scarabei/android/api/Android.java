
package com.jfixby.scarabei.android.api;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.display.DisplayMetrics;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.sys.SystemInfo;

public class Android {

	static private ComponentInstaller<AndroidComponent> componentInstaller = new ComponentInstaller<AndroidComponent>("Android");

	public static final void installComponent (final AndroidComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final AndroidComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final AndroidComponent component () {
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

	public static AndroidAppVersion getAppVersion () {
		return invoke().getAppVersion();
	}

	public static SystemInfo getSystemInfo () {
		return invoke().getSystemInfo();
	}

}
