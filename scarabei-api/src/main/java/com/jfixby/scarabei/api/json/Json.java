
package com.jfixby.scarabei.api.json;

import com.jfixby.scarabei.api.ComponentInstaller;

public class Json {

	static private ComponentInstaller<JsonComponent> componentInstaller = new ComponentInstaller<JsonComponent>("Json");

	public static final void installComponent (final JsonComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final JsonComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final JsonComponent component () {
		return componentInstaller.getComponent();
	}

	public static JsonString serializeToString (final Object object) {
		return invoke().serializeToString(object);
	}

	public static <T> T deserializeFromString (final Class<T> type, final String raw_json) {
		return invoke().deserializeFromString(type, raw_json);
	}

	public static <T> T deserializeFromString (final Class<T> type, final JsonString json) {
		return invoke().deserializeFromString(type, json);
	}

	public static void printPretty (final JsonString json_string) {
		invoke().printPretty(json_string);
	}

	public static JsonString newJsonString (final String raw_json_string) {
		return invoke().newJsonString(raw_json_string);
	}

}
