
package com.jfixby.scarabei.api.java.gc;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;

public class GCFisher {

	public static final ID DefaultBaitSize = Names.newID("GCFisher.DefaultBaitSize");

	public static final long AVERAGE_ANDROID_GC_DELAY = 150;// ms
	public static final long MB = 1024 * 1024;

	static private ComponentInstaller<GCFisherComponent> componentInstaller = new ComponentInstaller<GCFisherComponent>(
		"GCFisher");

	public static final void installComponent (final GCFisherComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
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

	public static boolean isGarbageModeFlag (final GARBAGE_MODE mode) {
		return invoke().isGarbageModeFlag(mode);
	}

	public static void setGarbageModeFlag (final GARBAGE_MODE mode) {
		invoke().setGarbageModeFlag(mode);
	}

	public static GARBAGE_MODE getGarbageModeFlag () {
		return invoke().getGarbageModeFlag();
	}

}
