
package com.jfixby.scarabei.api.flutter.plugins;

import io.flutter.plugin.common.PluginRegistry;

public interface FlutterPluginsComponent {

	FlutterPluginSpecs newPluginSpecs ();

	FlutterPlugin registerPlugin (FlutterPluginSpecs specs);

	PluginRegistry getFlutterPluginRegistry ();

}
