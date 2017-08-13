
package com.jfixby.scarabei.api.flutter.plugins;

import com.jfixby.scarabei.api.assets.ID;

import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class FlutterPluginSpecs {

	public Class methodCallHandlerClass;
	public ID methodCallHandlerClassName;
	public MethodCallHandler methodCallHandler;
	public ID channelName; // default value is methodCallHandler.getClass().getCanonicalName();

}
