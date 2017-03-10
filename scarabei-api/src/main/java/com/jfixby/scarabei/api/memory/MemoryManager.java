
package com.jfixby.scarabei.api.memory;

import com.jfixby.scarabei.api.ComponentInstaller;

public class MemoryManager {
	static private ComponentInstaller<MemoryManagerComponent> componentInstaller = new ComponentInstaller<MemoryManagerComponent>(
		"MemoryManager");

	public static final void installComponent (final MemoryManagerComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final MemoryManagerComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final MemoryManagerComponent component () {
		return componentInstaller.getComponent();
	}

	public static long getMaxHeapSize () {
		return invoke().getMaxHeapSize();
	}

	public static long getRecommendedHeapSize () {
		return invoke().getRecommendedHeapSize();
	}

}
