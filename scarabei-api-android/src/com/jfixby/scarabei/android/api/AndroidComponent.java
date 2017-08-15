
package com.jfixby.scarabei.android.api;

import com.jfixby.scarabei.api.display.DisplayMetrics;
import com.jfixby.scarabei.api.mobile.MobileDeviceComponent;

import android.content.Context;

public interface AndroidComponent extends MobileDeviceComponent {

	DisplayMetrics getDisplayMetrics ();

	double densityIndependentPixels2Pixels (float dip);

	Context getApplicationContext ();

}
