
package com.jfixby.cmns.aws.api;

import com.jfixby.cmns.api.ComponentInstaller;

public class AWS {

	static private ComponentInstaller<AWSComponent> componentInstaller = new ComponentInstaller<>("AWS");

	public static final void installComponent (final AWSComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static void installComponent (final String className, final ClassLoader classLoader) {
		componentInstaller.installComponent(className, classLoader);
	}

	public static final AWSComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final AWSComponent component () {
		return componentInstaller.getComponent();
	}

	public static S3 getS3 () {
		return invoke().getS3();
	}

}
