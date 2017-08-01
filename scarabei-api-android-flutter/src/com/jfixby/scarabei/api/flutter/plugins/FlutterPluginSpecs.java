
package com.jfixby.scarabei.api.flutter.plugins;

import com.jfixby.scarabei.api.assets.ID;

import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class FlutterPluginSpecs {

	public MethodCallHandler methodCallHandler;
	public ID methodCallHandlerClassName;
	public ID channelName; // default value is methodCallHandler.getClass().getCanonicalName();

}
