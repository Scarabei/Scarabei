
package com.jfixby.scarabei.ios.api;

import com.jfixby.scarabei.api.display.DisplayMetrics;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.mobile.MobileDeviceComponent;
import com.jfixby.scarabei.ios.api.camera.iOSCameraSetup;

public interface iOSComponent extends MobileDeviceComponent {

	iOSCameraSetup getCameraSetup ();

	File getDocuments ();

	File getLibrary ();

	File getTmp ();

	File getCaches ();

	File getLocal ();

	File getPreferences ();

	File getApplicationSupport ();

	DisplayMetrics getDisplayMetrics ();

	File getAssetsFolder ();

}
