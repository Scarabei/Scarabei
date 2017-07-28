
package com.jfixby.scarabei.red.flutter;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPlugin;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;

import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class RedFlutterPlugin implements FlutterPlugin {

	private final MethodCallHandler methodCallHandler;
	private final String name;
	private final RedFlutterPlugins master;

	public RedFlutterPlugin (final RedFlutterPlugins redFlutterPlugins, final FlutterPluginSpecs specs) {
		this.master = redFlutterPlugins;
		this.methodCallHandler = Debug.checkNull("methodCallHandler", specs.methodCallHandler);
		this.name = this.isValidName(specs.channelName) ? specs.channelName : this.methodCallHandler.getClass().getCanonicalName();
		this.master.registerPlugin(this.methodCallHandler, this.name);
	}

	final private boolean isValidName (final String channelName) {
		return !("".equals(channelName) || channelName == null);
	}

	@Override
	public String getName () {
		return this.name;
	}

	@Override
	public MethodCallHandler getHandler () {
		return this.methodCallHandler;
	}

}
