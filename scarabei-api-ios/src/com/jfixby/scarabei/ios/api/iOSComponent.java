
package com.jfixby.scarabei.ios.api;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.display.DisplayMetrics;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.ios.api.camera.iOSCameraSetup;

public interface iOSComponent {

	iOSCameraSetup getCameraSetup ();

	File getDocuments ();

	File getLibrary ();

	File getTmp ();

	File getCaches ();

	File getLocal ();

	File getPreferences ();

	File getApplicationSupport ();

	DisplayMetrics getDisplayMetrics ();

	iOSAppVersion getAppVersion ();

	Map<ID, String> getSystemInfo ();

	long getMaxHeapSize ();

	long getRecommendedHeapSize ();

	File getAssetsFolder ();

}
