
package com.jfixby.scarabei.api.flutter.plugins;

import com.jfixby.scarabei.api.ComponentInstaller;

import io.flutter.plugin.common.PluginRegistry;

public class FlutterPlugins {

	static private ComponentInstaller<FlutterPluginsComponent> componentInstaller = new ComponentInstaller<>("FlutterPlugins");

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final void installComponent (final FlutterPluginsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static FlutterPluginsComponent deInstallCurrentComponent () {
		return componentInstaller.deInstallCurrentComponent();
	}

	public static final FlutterPluginsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final FlutterPluginsComponent component () {
		return componentInstaller.getComponent();
	}

	public static FlutterPluginSpecs newPluginSpecs () {
		return invoke().newPluginSpecs();
	}

	public static FlutterPlugin registerPlugin (final FlutterPluginSpecs specs) {
		return invoke().registerPlugin(specs);
	}

	public static PluginRegistry getFlutterPluginRegistry () {
		return invoke().getFlutterPluginRegistry();
	}

}
