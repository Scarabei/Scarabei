
package com.jfixby.scarabei.red.flutter.plugins.android.sys;

import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class SharedPreferencesPlugin implements MethodCallHandler {

	public static FlutterPluginSpecs filloutRegistration (final FlutterPluginSpecs specs) {
		specs.methodCallHandler = new SharedPreferencesPlugin();
		return specs;
	}

	@Override
	public void onMethodCall (final MethodCall call, final MethodChannel.Result result) {
		final String key = (String)call.argument("key");

		switch (call.method) {
		case "setBool":
			SystemSettings.setFlag(key, (boolean)call.argument("value"));
			result.success(null);
			break;
		case "setInt":
			final Number number = (Number)call.argument("value");
			SystemSettings.setIntParameter(key, number.longValue());
			result.success(null);
			break;
		case "setString":
			SystemSettings.setStringParameter(key, (String)call.argument("value"));
			result.success(null);
			break;
		case "getAll":
			result.success(SystemSettings.listAllSettings().toJavaMap());
			break;
		case "clear":
			SystemSettings.clearAll();
			break;
		default:
			result.notImplemented();
			break;
		}

	}
}
