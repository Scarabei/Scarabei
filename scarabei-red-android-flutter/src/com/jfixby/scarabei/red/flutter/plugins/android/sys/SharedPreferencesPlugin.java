
package com.jfixby.scarabei.red.flutter.plugins.android.sys;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.log.L;
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
		final String k = (String)call.argument("key");
		final ID key = Names.newID(k);

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
			result.success(null);
			break;
		case "saveToStorege":
			try {
				final Boolean save = SystemSettings.saveToStorage().await();
				result.success(save);
			} catch (final Throwable e) {
				e.printStackTrace();
				result.error(e.toString(), L.stackTraceToString(e), e);
// result.success(false);
			}
			break;
		default:
			result.notImplemented();
			break;
		}

	}
}
