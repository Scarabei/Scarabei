package com.jfixby.cmns.api.json;

import com.jfixby.cmns.api.components.ComponentInstaller;

public class Json {

	static private ComponentInstaller<JsonComponent> componentInstaller = new ComponentInstaller<JsonComponent>(
			"Json");

	public static final void installComponent(JsonComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final JsonComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final JsonComponent component() {
		return componentInstaller.getComponent();
	}

	public static String serializeToString(Object object) {
		return invoke().serializeToString(object);
	}

	public static <T> T deserializeFromString(Class<T> type, String input_string) {
		return invoke().deserializeFromString(type, input_string);
	}

	public static void printPretty(String json_string) {
		invoke().printPretty(json_string);
	}

}
