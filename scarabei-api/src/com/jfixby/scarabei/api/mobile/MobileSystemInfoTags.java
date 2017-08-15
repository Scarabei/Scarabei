
package com.jfixby.scarabei.api.mobile;

import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;

public class MobileSystemInfoTags {

	public static final ID Brand = Names.newID("mobile.device.brand");
	public static final ID Model = Names.newID("mobile.device.model");
	public static final ID Host = Names.newID("mobile.device.host");
	public static final ID Serial = Names.newID("mobile.device.serial");
	public static final ID Release = Names.newID("mobile.device.release");
	public static final ID Fingerprint = Names.newID("mobile.device.fingerprint");
	public static final ID Manufacturer = Names.newID("mobile.device.manufacturer");

	public static class Display {
		public static final ID WIDTH = Names.newID("mobile.device.display.width");
		public static final ID HEIGHT = Names.newID("mobile.device.display.height");
	}

	public static class App {
		public static class Version {
			public static final ID Name = Names.newID("mobile.device.app.version.name");
			public static final ID Code = Names.newID("mobile.device.app.version.code");
			public static final ID PackageName = Names.newID("mobile.device.app.version.package_name");
		}

	}
}
