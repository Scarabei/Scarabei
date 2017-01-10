
package com.jfixby.scarabei.api.sys;

public class SystemInfoTags {

	public static class System {
		public static final String OS_NAME = "os.name";
		public static final String OS_VERSION = "os.version";
	}

	public static class Java {
		public static final String Version = "java.version";
	}

	public static class Net {
		public static final String client_ip = "net.client_ip";
	}

	public static class Desktop {
		public static class Screen {
			public static final String WIDTH = "desktop.screen.width";
			public static final String HEIGHT = "desktop.screen.height";
		}

	}

}
