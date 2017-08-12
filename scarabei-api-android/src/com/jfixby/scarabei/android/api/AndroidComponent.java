
package com.jfixby.scarabei.android.api;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.display.DisplayMetrics;
import com.jfixby.scarabei.api.file.File;

import android.content.Context;

public interface AndroidComponent {

	long getMaxHeapSize ();

	long getRecommendedHeapSize ();

	String getApplicationPrivateDirPathString ();

// AndroidCameraSetup getCameraSetup ();

	File getPrivateFolder ();

	File getCacheFolder ();

	DisplayMetrics getDisplayMetrics ();

	String getBrand ();

	String getModel ();

	String getHost ();

	String getVersionRelease ();

	AndroidAppVersion getAppVersion ();

	Map<ID, String> getSystemInfo ();

	String getSerial ();

	String getFingerPrint ();

	String getManufacturer ();

	double densityIndependentPixels2Pixels (float dip);

	Context getApplicationContext ();

}
