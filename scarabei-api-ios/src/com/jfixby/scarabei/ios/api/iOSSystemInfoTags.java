
package com.jfixby.scarabei.ios.api;

public class iOSSystemInfoTags {

	public static final String Brand = "ios.brand";
	public static final String Model = "ios.model";
	public static final String Host = "ios.host";
	public static final String Serial = "ios.serial";
	public static final String Release = "ios.release";
	public static final String Fingerprint = "ios.fingerprint";
	public static final String Manufacturer = "ios.manufacturer";

	public static class Display {
		public static final String WIDTH = "ios.display.width";
		public static final String HEIGHT = "ios.display.height";
	}

	public static class App {
		public static class Version {
			public static final String Name = "ios.app.version.name";
			public static final String Code = "ios.app.version.code";
			public static final String PackageName = "ios.app.version.package_name";
		}

	}
}
