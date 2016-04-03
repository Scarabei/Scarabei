package com.jfixby.cmns.api.json;

import com.jfixby.cmns.api.ComponentInstaller;

public class Json {

    static private ComponentInstaller<JsonComponent> componentInstaller = new ComponentInstaller<JsonComponent>("Json");

    public static final void installComponent(JsonComponent component_to_install) {
	componentInstaller.installComponent(component_to_install);
    }

    public static final JsonComponent invoke() {
	return componentInstaller.invokeComponent();
    }

    public static final JsonComponent component() {
	return componentInstaller.getComponent();
    }

    public static JsonString serializeToString(Object object) {
	return invoke().serializeToString(object);
    }

    public static <T> T deserializeFromString(Class<T> type, String raw_json) {
	return invoke().deserializeFromString(type, raw_json);
    }

    public static <T> T deserializeFromString(Class<T> type, JsonString json) {
	return invoke().deserializeFromString(type, json);
    }

    public static void printPretty(JsonString json_string) {
	invoke().printPretty(json_string);
    }

    public static JsonString newJsonString(String raw_json_string) {
	return invoke().newJsonString(raw_json_string);
    }

}
