
package com.jfixby.scarabei.android.api;

import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;

public class AndroidSystemInfoTags {

	public static final ID Brand = Names.newID("android.brand");
	public static final ID Model = Names.newID("android.model");
	public static final ID Host = Names.newID("android.host");
	public static final ID Serial = Names.newID("android.serial");
	public static final ID Release = Names.newID("android.release");
	public static final ID Fingerprint = Names.newID("android.fingerprint");
	public static final ID Manufacturer = Names.newID("android.manufacturer");

	public static class Display {
		public static final ID WIDTH = Names.newID("android.display.width");
		public static final ID HEIGHT = Names.newID("android.display.height");
	}

	public static class App {
		public static class Version {
			public static final ID Name = Names.newID("android.app.version.name");
			public static final ID Code = Names.newID("android.app.version.code");
			public static final ID PackageName = Names.newID("android.app.version.package_name");
		}

	}
}
