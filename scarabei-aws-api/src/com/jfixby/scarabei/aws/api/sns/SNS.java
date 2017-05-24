
package com.jfixby.scarabei.aws.api.sns;

import com.jfixby.scarabei.api.ComponentInstaller;

public class SNS {

	static private ComponentInstaller<SNSComponent> componentInstaller = new ComponentInstaller<SNSComponent>("SNSComponent");

	public static final void installComponent (final SNSComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static void installComponent (final String className, final ClassLoader classLoader) {
		componentInstaller.installComponent(className, classLoader);
	}

	public static final SNSComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final SNSComponent component () {
		return componentInstaller.getComponent();
	}

}
