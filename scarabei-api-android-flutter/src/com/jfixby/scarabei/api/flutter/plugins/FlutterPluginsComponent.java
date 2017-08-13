
package com.jfixby.scarabei.api.flutter.plugins;

public interface FlutterPluginsComponent {

	FlutterPluginSpecs newPluginSpecs ();

	FlutterPlugin registerPlugin (FlutterPluginSpecs specs);

}
