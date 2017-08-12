
package com.jfixby.scarabei.red.flutter.plugins.android.sys;

import java.lang.reflect.Method;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class SharedPreferencesPlugin implements MethodCallHandler {

	public static FlutterPluginSpecs filloutRegistration (final FlutterPluginSpecs specs) {
		specs.methodCallHandler = new SharedPreferencesPlugin();
		return specs;
	}

	@Override
	public void onMethodCall (final MethodCall call, final MethodChannel.Result result) {
		final String jsonString = (String)call.argument("jsonString");
		L.d("jsonString", jsonString);
		result.notImplemented();

	}

	private List<CallArgument> extractArguments (final String rawJson) {
		final List<CallArgument> arguments = Collections.newList();
		final String[] jsonsList = Json.deserializeFromString(String[].class, rawJson);
		for (int i = 0; i < jsonsList.length; i++) {
			final String str = jsonsList[i];
			final String[] list = Json.deserializeFromString(String[].class, rawJson);
			final CallArgument arg = new CallArgument();

		}

		return arguments;
	}

	private boolean callMethod (final String methodName, final ID key, final Object value, final MethodCall call) {
		final Class<SystemSettings> klass = SystemSettings.class;
		try {
			final Method registerWith = klass.getMethod(methodName, Registrar.class);
		} catch (final NoSuchMethodException e) {
			e.printStackTrace();
		} catch (final SecurityException e) {
			e.printStackTrace();
		}

		return false;
	}
}
