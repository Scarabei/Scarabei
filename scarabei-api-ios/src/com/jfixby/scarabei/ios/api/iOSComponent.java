
package com.jfixby.scarabei.ios.api;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.sys.SystemInfo;
import com.jfixby.scarabei.ios.api.camera.iOSCameraSetup;

public interface iOSComponent {

	long getMaxHeapSize ();

	long getRecommendedHeapSize ();

	String getApplicationPrivateDirPathString ();

	iOSCameraSetup getCameraSetup ();

	File getPrivateFolder ();

	File getCacheFolder ();

	DisplayMetrics getDisplayMetrics ();

	String getBrand ();

	String getModel ();

	String getHost ();

	String getVersionRelease ();

	iOSAppVersion getAppVersion ();

	SystemInfo getSystemInfo ();

	String getSerial ();

	String getFingerPrint ();

	String getManufacturer ();

}
