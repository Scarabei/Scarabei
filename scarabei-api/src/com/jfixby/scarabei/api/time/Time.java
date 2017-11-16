
package com.jfixby.scarabei.api.time;

import com.jfixby.scarabei.api.ComponentInstaller;

public class Time {

	static private ComponentInstaller<TimeComponent> componentInstaller = new ComponentInstaller<TimeComponent>("Time");

	public static final void installComponent (final TimeComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final TimeComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final TimeComponent component () {
		return componentInstaller.getComponent();
	}

	public static TimeComponent deInstallComponent () {
		return componentInstaller.deInstallCurrentComponent();
	}

	public static ResetableTimeStream newResetableTimeStream (final TimeStream systemTime) {
		return invoke().newResetableTimeStream(systemTime);
	}

}
