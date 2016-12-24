
package com.jfixby.scarabei.api.input;

import com.jfixby.scarabei.api.ComponentInstaller;

public class UserInput {

	static private ComponentInstaller<UserInputComponent> componentInstaller = new ComponentInstaller<UserInputComponent>(
		"UserInput");

	public static final void installComponent (UserInputComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
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
