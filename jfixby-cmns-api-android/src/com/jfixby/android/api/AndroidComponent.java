
package com.jfixby.android.api;

import com.jfixby.android.api.camera.AndroidCameraSetup;

public interface AndroidComponent {

	long getMaxHeapSize ();

	long getRecommendedHeapSize ();

	String getApplicationPrivateDirPathString ();

	AndroidCameraSetup getCameraSetup ();

}
