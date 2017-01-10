
package com.jfixby.scarabei.ios.api;

public class iOSSystemInfoTags {

	public static class Device {
		public static final String Model = "ios.device.model";
		public static final String Name = "ios.device.name";
		public static final String LocalizedModel = "ios.device.localized_model";
		public static final String IdentifierForVendor = "ios.device.identifier_for_vendor";
	}

	public static class System {
		public static final String Name = "ios.device.system.name";
		public static final String Version = "ios.device.system.version";
	}

	public static class Display {
		public static final String WIDTH = "ios.display.width";
		public static final String HEIGHT = "ios.display.height";
	}

	public static class Battery {
		public static final String Level = "ios.battery.level";
		public static final String State = "ios.battery.state";
	}

	public static class App {
		public static class Version {
			public static final String Name = "ios.app.version.name";
			public static final String Build = "ios.app.version.build";
			public static final String PackageName = "ios.app.version.package_name";
		}

	}
}
