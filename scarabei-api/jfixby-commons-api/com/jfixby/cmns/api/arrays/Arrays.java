
package com.jfixby.cmns.api.arrays;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;

public class Arrays {

	static private ComponentInstaller<ArraysComponent> componentInstaller = new ComponentInstaller<ArraysComponent>("Arrays");

	public static final void installComponent (final ArraysComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final ArraysComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final ArraysComponent component () {
		return componentInstaller.getComponent();
	}

	public static final <T> List<T> newList (final T[] array) {
		return invoke().newList(array);
	}

	public static final <T> Set<T> newSet (final T[] array) {
		return invoke().newSet(array);
	}

	public static List<Float> newFloatsList (final float[] floats) {
		return invoke().newFloatsList(floats);
	}

	public static List<Integer> newIntsList (final int[] ints) {
		return invoke().newIntsList(ints);
	}

}
