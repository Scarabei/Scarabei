
package com.jfixby.scarabei.api.localize;

import com.jfixby.scarabei.api.ComponentInstaller;

public class Localization {

	static private ComponentInstaller<LocalizationComponent> componentInstaller = new ComponentInstaller<LocalizationComponent>(
		"Localization");

	public static final void installComponent (LocalizationComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final LocalizationComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final LocalizationComponent component () {
		return componentInstaller.getComponent();
	}

	public static StringValuesContainer getStringValuesContainer () {
		return invoke().getStringValuesContainer();
	}

	public static Locale getLocale (String locale_name) {
		return invoke().getLocale(locale_name);
	}

}
