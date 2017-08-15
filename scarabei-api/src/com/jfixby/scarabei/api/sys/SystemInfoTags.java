
package com.jfixby.scarabei.api.sys;

import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;

public class SystemInfoTags {

	public static class System {
		public static final ID OS_NAME = Names.newID("os.name");
		public static final ID OS_VERSION = Names.newID("os.version");
	}

	public static class Java {
		public static final ID Version = Names.newID("java.version");
		public static final ID MaxHeapSize = Names.newID("java.heap.max");
		public static final ID RecommendedHeapSize = Names.newID("java.heap.recommended");
	}

	public static class Net {
		public static final ID client_ip = Names.newID("net.client_ip");
	}

	public static class Desktop {
		public static class Screen {
			public static final ID WIDTH = Names.newID("desktop.screen.width");
			public static final ID HEIGHT = Names.newID("desktop.screen.height");
		}

	}

}
