
package com.jfixby.scarabei.aws.api.sqs;

import com.jfixby.scarabei.api.ComponentInstaller;

public class SQS {

	static private ComponentInstaller<SQSComponent> componentInstaller = new ComponentInstaller<SQSComponent>("SQSComponent");

	public static final void installComponent (final SQSComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static void installComponent (final String className, final ClassLoader classLoader) {
		componentInstaller.installComponent(className, classLoader);
	}

	public static final SQSComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final SQSComponent component () {
		return componentInstaller.getComponent();
	}

}
