
package com.jfixby.scarabei.api.flutter.plugins;

import io.flutter.plugin.common.PluginRegistry;

public interface FlutterPluginsComponent {

	FlutterPluginSpecs newPluginSpecs ();

	FlutterPlugin registerPlugin (FlutterPluginSpecs specs);

	PluginRegistry getFlutterPluginRegistry ();

	public void activatePluginRegistry (PluginRegistry reg);

	public PluginRegistry deActivatePluginRegistry ();

}
