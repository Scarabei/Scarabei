
package com.jfixby.scarabei.android.api;

import com.jfixby.scarabei.android.api.camera.AndroidCameraSetup;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.sys.SystemInfo;

public interface AndroidComponent {

	long getMaxHeapSize ();

	long getRecommendedHeapSize ();

	String getApplicationPrivateDirPathString ();

	AndroidCameraSetup getCameraSetup ();

	File getPrivateFolder ();

	File getCacheFolder ();

	DisplayMetrics getDisplayMetrics ();

	String getBrand ();

	String getModel ();

	String getHost ();

	String getVersionRelease ();

	AndroidAppVersion getAppVersion ();

	SystemInfo getSystemInfo ();

	String getSerial ();

	String getFingerPrint ();

	String getManufacturer ();

}
