
package com.jfixby.scarabei.api.random;

import com.jfixby.scarabei.api.ComponentInstaller;

public class Random {

	static private ComponentInstaller<RandomComponent> componentInstaller = new ComponentInstaller<RandomComponent>("Random");

	public static final void installComponent (final RandomComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final RandomComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final RandomComponent component () {
		return componentInstaller.getComponent();
	}

	public static int newInt (final int min, final int max) {
		return invoke().newInt(min, max);
	}

	public static double newDouble (final double min, final double max) {
		return invoke().newDouble(min, max);
	}

	public static boolean newCoin () {
		return invoke().newCoin();
	}

	public static int newInt32 () {
		return invoke().newInt32();
	}

	public static void setSeed (final long seed) {
		invoke().setSeed(seed);
	}

}
