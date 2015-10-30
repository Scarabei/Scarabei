package com.jfixby.cmns.api.md5;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.components.ComponentInstaller;

public class MD5 {
	static private ComponentInstaller<MD5Component> componentInstaller = new ComponentInstaller<MD5Component>("MD5");

	public static final void installComponent(MD5Component component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final MD5Component invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final MD5Component component() {
		return componentInstaller.getComponent();
	}

	public static String md5Stream(InputStream java_input_stream) throws IOException {
		return invoke().md5Stream(java_input_stream);
	}

	public static String md5String(String password) {
		return invoke().md5String(password);
	}
}
