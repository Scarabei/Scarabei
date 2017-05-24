
package com.jfixby.scarabei.aws.api.ses;

import com.jfixby.scarabei.api.ComponentInstaller;

public class SES {

	static private ComponentInstaller<SESComponent> componentInstaller = new ComponentInstaller<SESComponent>("SESComponent");

	public static final void installComponent (final SESComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static void installComponent (final String className, final ClassLoader classLoader) {
		componentInstaller.installComponent(className, classLoader);
	}

	public static final SESComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final SESComponent component () {
		return componentInstaller.getComponent();
	}

}
