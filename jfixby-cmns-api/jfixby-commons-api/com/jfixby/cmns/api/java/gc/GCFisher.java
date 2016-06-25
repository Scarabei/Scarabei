
package com.jfixby.cmns.api.java.gc;

import com.jfixby.cmns.api.ComponentInstaller;

public class GCFisher {

	public static final String DefaultBaitSize = "GCFisher.DefaultBaitSize";

	public static final long AVERAGE_ANDROID_GC_DELAY = 150;// ms
	public static final long MB = 1024 * 1024;

	static private ComponentInstaller<GCFisherComponent> componentInstaller = new ComponentInstaller<GCFisherComponent>(
		"GCFisher");

	public static final void installComponent (final GCFisherComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final GCFisherComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final GCFisherComponent component () {
		return componentInstaller.getComponent();
	}

	public static BaitInfo throwBait (final long size_in_bytes, final boolean isReinforcable) {
		return invoke().throwBait(size_in_bytes, isReinforcable);
	}

	public static void forceGC (final long bait_size_in_bytes) {
		invoke().forceGC(bait_size_in_bytes);
	}

	public static MemoryStatistics getMemoryStatistics () {
		return invoke().getMemoryStatistics();
	}

}
