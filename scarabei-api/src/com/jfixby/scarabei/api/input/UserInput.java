
package com.jfixby.scarabei.api.input;

import com.jfixby.scarabei.api.ComponentInstaller;

public class UserInput {

	static private ComponentInstaller<UserInputComponent> componentInstaller = new ComponentInstaller<UserInputComponent>(
		"UserInput");

	public static final void installComponent (final UserInputComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final UserInputComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final UserInputComponent component () {
		return componentInstaller.getComponent();
	}

	public static Keyboard Keyboard () {
		return invoke().Keyboard();
	}

	public static MouseButtons MouseButtons () {
		return invoke().MouseButtons();
	}

}
