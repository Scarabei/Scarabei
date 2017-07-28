
package com.jfixby.scarabei.api.flutter.plugins;

import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public interface FlutterPlugin {

	public String getName ();

	public MethodCallHandler getHandler ();
}
