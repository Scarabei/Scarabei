
package com.jfixby.scarabei.aws.api.s3;

import com.jfixby.scarabei.api.ComponentInstaller;

public class S3 {

	static private ComponentInstaller<S3Component> componentInstaller = new ComponentInstaller<S3Component>("S3Component");

	public static final void installComponent (final S3Component component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static void installComponent (final String className, final ClassLoader classLoader) {
		componentInstaller.installComponent(className, classLoader);
	}

	public static final S3Component invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final S3Component component () {
		return componentInstaller.getComponent();
	}

}
