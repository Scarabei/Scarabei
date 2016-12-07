
package com.jfixby.cmns.api.md5;

import java.io.IOException;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.io.InputStream;

public class MD5 {
	static private ComponentInstaller<MD5Component> componentInstaller = new ComponentInstaller<MD5Component>("MD5");

	public static final void installComponent (final MD5Component component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final MD5Component invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final MD5Component component () {
		return componentInstaller.getComponent();
	}

	public static String md5Stream (final InputStream java_input_stream) throws IOException {
		return invoke().md5Stream(java_input_stream);
	}

	public static String md5String (final String password) {
		return invoke().md5String(password);
	}
}
