
package com.jfixby.scarabei.api.mobile;

import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.file.LocalFile;
import com.jfixby.scarabei.api.names.ID;

public interface MobileDeviceComponent {

	long getMaxHeapSize();

	long getRecommendedHeapSize();

	String getApplicationPrivateDirPathString();

	LocalFile getPrivateFolder();

	LocalFile getCacheFolder();

	String getBrand();

	String getModel();

	String getHost();

	String getVersionRelease();

	MobileAppVersion getAppVersion();

	Map<ID, Object> getSystemInfo();

	String getSerial();

	String getFingerPrint();

	String getManufacturer();

}
