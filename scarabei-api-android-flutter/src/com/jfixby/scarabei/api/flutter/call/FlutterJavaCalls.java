
package com.jfixby.scarabei.api.flutter.call;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.LambdaMap;

public class FlutterJavaCalls {

	static private ComponentInstaller<FlutterJavaCallsComponent> componentInstaller = new ComponentInstaller<>("FlutterPlugins");

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final void installComponent (final FlutterJavaCallsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static FlutterJavaCallsComponent deInstallCurrentComponent () {
		return componentInstaller.deInstallCurrentComponent();
	}

	public static final FlutterJavaCallsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final FlutterJavaCallsComponent component () {
		return componentInstaller.getComponent();
	}

	public static Object decode (final Object flutterObject, final LambdaMap<Object, String> objectTypeNames) {
		return invoke().decode(flutterObject, objectTypeNames);
	}

	public static Object encode (final Object javaObject) {
		return invoke().encode(javaObject);
	}

	public static JavaMethodCall decodeMethodCall (final FlutterMethodCall flutterCall) {
		return invoke().decodeMethodCall(flutterCall);
	}

}
