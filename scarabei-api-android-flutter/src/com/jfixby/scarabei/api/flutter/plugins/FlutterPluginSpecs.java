
package com.jfixby.scarabei.api.flutter.plugins;

import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class FlutterPluginSpecs {

	public MethodCallHandler methodCallHandler;
	public String channelName; // default value is methodCallHandler.getClass().getCanonicalName();

}
