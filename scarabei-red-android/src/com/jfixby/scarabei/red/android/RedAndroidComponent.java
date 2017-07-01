
package com.jfixby.scarabei.red.android;

import com.jfixby.scarabei.android.api.AndroidAppVersion;
import com.jfixby.scarabei.android.api.AndroidComponent;
import com.jfixby.scarabei.android.api.AndroidSystemInfoTags;
import com.jfixby.scarabei.api.display.DisplayMetrics;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.sys.SystemInfo;
import com.jfixby.scarabei.api.sys.SystemInfoTags;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.api.ver.Version;
import com.jfixby.scarabei.red.sys.RedDeviceInfo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.TypedValue;

public class RedAndroidComponent implements AndroidComponent {
	Context context;
	private final Activity app;

	public RedAndroidComponent (final Activity redTriplaneAndroidApplication) {
		this.app = redTriplaneAndroidApplication;
		this.context = redTriplaneAndroidApplication.getBaseContext();
	}

	@Override
	public double densityIndependentPixels2Pixels (final float dip) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, this.context.getResources().getDisplayMetrics());
	}

	@Override
	public long getRecommendedHeapSize () {
		final ActivityManager am = (ActivityManager)this.app.getSystemService(Context.ACTIVITY_SERVICE);
		final int memoryClass = am.getMemoryClass();
		return memoryClass;
	}

	@Override
	public long getMaxHeapSize () {
		final Runtime rt = Runtime.getRuntime();
		final long maxMemory = rt.maxMemory() / (1024 * 1024);
		// Log.v("onCreate", "maxMemory:" + Long.toString(maxMemory));
		return maxMemory;
	}

	@Override
	public String getApplicationPrivateDirPathString () {
// final String java_path = this.app.getApplication().getApplicationContext().getFilesDir().getAbsolutePath();
		final String java_path = this.context.getFilesDir().getAbsolutePath();
		return java_path;
	}

// @Override
// public AndroidCameraSetup getCameraSetup () {
// return this.cameraSetup;
// }
//
// public void prepareCamera () {
// this.cameraSetup.prepareCamera();
// }
//
// public void activateCamera () {
// this.cameraSetup.activateCamera();
// }

	@Override
	public File getPrivateFolder () {
		final String path = this.getApplicationPrivateDirPathString();
		final File privateFolder = LocalFileSystem.newFile(path);
		return privateFolder;
	}

	@Override
	public File getCacheFolder () {
		final java.io.File cache = this.app.getCacheDir();
		return LocalFileSystem.newFile(cache);
	}

	@Override
	public DisplayMetrics getDisplayMetrics () {
		final DisplayMetrics displayMetrics = new DisplayMetrics();
		final android.util.DisplayMetrics dm = new android.util.DisplayMetrics();
		try {
			this.app.getWindowManager().getDefaultDisplay().getMetrics(dm);
		} catch (final Exception e) {
			Err.reportError(e);
		}
		final int height = dm.heightPixels;
		final int width = dm.widthPixels;

		displayMetrics.set(width, height);

		return displayMetrics;
	}

	@Override
	public String getBrand () {
		return Build.BRAND;
	}

	@Override
	public String getSerial () {
		return Build.SERIAL;
	}

	@Override
	public String getModel () {
		return Build.MODEL;
	}

	@Override
	public String getFingerPrint () {
		return Build.FINGERPRINT;
	}

	@Override
	public String getManufacturer () {
		return Build.MANUFACTURER;
	}

	@Override
	public String getHost () {
		return Build.HOST;
	}

	@Override
	public String getVersionRelease () {
		return Build.VERSION.RELEASE;
	}

	@Override
	public AndroidAppVersion getAppVersion () {
		final RedAndroidAppVersion version = new RedAndroidAppVersion();

		try {
			final PackageInfo pInfo = this.app.getPackageManager().getPackageInfo(this.app.getPackageName(), 0);
			version.package_name = this.app.getPackageName();
			version.name = pInfo.versionName;
			version.code = pInfo.versionCode;

		} catch (final NameNotFoundException e) {
			e.printStackTrace();
		}

		return version;
	}

	@Override
	public SystemInfo getSystemInfo () {
		final RedDeviceInfo deviceInfo = new RedDeviceInfo();
		{
			final DisplayMetrics displayMetrics = this.getDisplayMetrics();
			final double height = displayMetrics.getHeight();
			final double width = displayMetrics.getWidth();
			deviceInfo.putValue(AndroidSystemInfoTags.Display.WIDTH, width);
			deviceInfo.putValue(AndroidSystemInfoTags.Display.HEIGHT, height);
		}
		{
			final String brand = this.getBrand();
			deviceInfo.putValue(AndroidSystemInfoTags.Brand, brand);
		}
		{
			final String value = this.getSerial();
			deviceInfo.putValue(AndroidSystemInfoTags.Serial, value);
		}

		{
			final String value = this.getFingerPrint();
			deviceInfo.putValue(AndroidSystemInfoTags.Fingerprint, value);
		}

		{
			final String value = this.getManufacturer();
			deviceInfo.putValue(AndroidSystemInfoTags.Manufacturer, value);
		}

		{
			final String model = this.getModel();
			deviceInfo.putValue(AndroidSystemInfoTags.Model, model);
		}
		{
			final String release = this.getVersionRelease();
			deviceInfo.putValue(AndroidSystemInfoTags.Release, release);
		}

		{
			final AndroidAppVersion version = this.getAppVersion();
			deviceInfo.putValue(AndroidSystemInfoTags.App.Version.Name, version.getName());
			deviceInfo.putValue(AndroidSystemInfoTags.App.Version.Code, version.getCode());
			deviceInfo.putValue(AndroidSystemInfoTags.App.Version.PackageName, version.getPackageName());
		}

		{
			final String host = this.getHost();
			deviceInfo.putValue(AndroidSystemInfoTags.Host, host);
		}

		{
			deviceInfo.putValue(SystemInfoTags.System.OS_NAME, System.getProperty("os.name"));
			deviceInfo.putValue(SystemInfoTags.System.OS_VERSION, System.getProperty("os.version"));

		}

		{
			deviceInfo.putValue(Version.Tags.PackageName, SystemSettings.getStringParameter(Version.Tags.PackageName));
			deviceInfo.putValue(Version.Tags.VersionCode, SystemSettings.getStringParameter(Version.Tags.VersionCode));
			deviceInfo.putValue(Version.Tags.VersionName, SystemSettings.getStringParameter(Version.Tags.VersionName));
		}

		return deviceInfo;
	}
}
