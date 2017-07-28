
package com.jfixby.scarabei.red.flutter.example;

import com.jfixby.scarabei.android.api.Android;
import com.jfixby.scarabei.api.log.L;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

public class ExamplePlugin implements MethodCallHandler {

	@Override
	public void onMethodCall (final MethodCall call, final Result result) {
		L.d("ExamplePlugin.onMethodCall", call.method);
		if (call.method.equals("getPlatformVersion")) {
			final String message = "Android " + android.os.Build.VERSION.RELEASE + " " + Android.getSystemInfo();
			result.success(message);
		} else {
			result.notImplemented();
		}
	}
}
