package com.jfixby.cmns.api.arrays;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;

public class Arrays {

	static private ComponentInstaller<ArraysComponent> componentInstaller = new ComponentInstaller<ArraysComponent>("Arrays");

	public static final void installComponent(ArraysComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final ArraysComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final ArraysComponent component() {
		return componentInstaller.getComponent();
	}

	public static final <T> List<T> newList(T[] array) {
		return invoke().newList(array);
	}

	public static final <T> Set<T> newSet(T[] array) {
		return invoke().newSet(array);
	}

	public static List<Float> newFloatsList(float[] floats) {
		return invoke().newFloatsList(floats);
	}

	public static List<Integer> newIntsList(int[] ints) {
		return invoke().newIntsList(ints);
	}

}
