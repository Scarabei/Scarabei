
package com.jfixby.scarabei.android.api;

public class AndroidSystemInfoTags {

	public static final String Brand = "android.brand";
	public static final String Model = "android.model";
	public static final String Host = "android.host";
	public static final String Serial = "android.serial";
	public static final String Release = "android.release";
	public static final String Fingerprint = "android.fingerprint";
	public static final String Manufacturer = "android.manufacturer";

	public static class Display {
		public static final String WIDTH = "android.display.width";
		public static final String HEIGHT = "android.display.height";
	}

	public static class App {
		public static class Version {
			public static final String Name = "android.app.version.name";
			public static final String Code = "android.app.version.code";
			public static final String PackageName = "android.app.version.package_name";
		}

	}
}
