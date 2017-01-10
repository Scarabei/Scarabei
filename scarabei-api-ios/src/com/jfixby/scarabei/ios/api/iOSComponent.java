
package com.jfixby.scarabei.ios.api;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.sys.SystemInfo;
import com.jfixby.scarabei.ios.api.camera.iOSCameraSetup;

public interface iOSComponent {

	iOSCameraSetup getCameraSetup ();

	File getDocuments ();

	File getLibrary ();

	File getTmp ();

	File getCaches ();

	File getLocal ();

	File getPreferences ();

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
