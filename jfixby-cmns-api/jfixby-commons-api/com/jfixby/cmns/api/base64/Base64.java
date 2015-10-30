package com.jfixby.cmns.api.base64;

import java.io.IOException;

import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.io.InputStream;

public class Base64 {
	static private ComponentInstaller<Base64Component> componentInstaller = new ComponentInstaller<Base64Component>(
			"Base64");

	public static final void installComponent(
			Base64Component component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final Base64Component invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final Base64Component component() {
		return componentInstaller.getComponent();
	}

	public static String encode(InputStream is) throws IOException {
		return invoke().encode(is);
	}
	
	public static String encode(byte[] data) throws IOException {
		return invoke().encode(data);
	}

	public static byte[] decode(String dataInBase64) throws IOException{
		return invoke().decode(dataInBase64);
	}

}
